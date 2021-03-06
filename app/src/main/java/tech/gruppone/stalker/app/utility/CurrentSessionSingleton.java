package tech.gruppone.stalker.app.utility;

import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.auth0.android.jwt.JWT;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

@SuppressLint("KotlinPropertyAccess")
public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  private final MutableLiveData<User> loggedUser = new MutableLiveData<>();

  @Getter private String jwt = "";
  @Getter private String anonymousJwt = "";

  @Getter @Setter private boolean anonymous = false;

  private MutableLiveData<Map<Long, LiveData<UserOrganizationHistory>>> userOrganizationHistory =
      new MutableLiveData<>(new TreeMap<>());

  @SuppressLint("UseSparseArrays")
  private final MutableLiveData<Map<Integer, LiveData<Organization>>> organizations =
      new MutableLiveData<>(new TreeMap<>());

  private CurrentSessionSingleton() {}

  public void setUser(@NonNull User user) {
    loggedUser.postValue(user);
  }

  public static void logout() {
    WebSingleton.getInstance()
        .logout(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            null,
            null);
  }

  @NonNull
  public LiveData<User> getLoggedUser() {
    return loggedUser;
  }

  public void setJwt(@NonNull String token) {
    jwt = token;

    // Suppressed check on null
    // If the token is invalid, the JWT constructor throws
    // If the token is valid, then it's coming out of our server, and so it holds the id
    @SuppressWarnings("ConstantConditions")
    final int id = Integer.parseInt(new JWT(token).getSubject());

    this.setUser(User.builder().id(id).build());

    WebSingleton.getInstance()
        .getAnonymousJwt(
            response -> {
              try {
                CurrentSessionSingleton.this.setAnonymousJwt(response.getString("anonymousJwt"));
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  private void setAnonymousJwt(@NonNull String anonymousToken) {
    anonymousJwt = anonymousToken;

    final int id = requireNonNull(CurrentSessionSingleton.this.loggedUser.getValue()).getId();

    WebSingleton.getInstance()
        .getUserInfo(
            id,
            jsonObject -> {
              try {
                JSONObject userData = jsonObject.getJSONObject("data");

                String email = userData.getString("email");
                String firstName = userData.getString("firstName");
                String lastName = userData.getString("lastName");
                String birthDate = userData.getString("birthDate");

                CurrentSessionSingleton.this.setUser(
                    User.builder()
                        .id(id)
                        .email(email)
                        .firstName(firstName)
                        .lastName(lastName)
                        .birthDate(birthDate)
                        .build());
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  public void setOrganizationList(@NonNull List<Organization> orgList) {
    /*@SuppressLint("UseSparseArrays")
    Map<Integer, LiveData<Organization>> map = new TreeMap<>();

    for (Organization organization : orgList) {
      map.put(organization.getId(), new MutableLiveData<>(organization));
    }

    organizations.postValue(map);*/

    Map<Integer, LiveData<Organization>> organizationsMap =
        requireNonNull(getOrganizations().getValue());

    // Changes to the keySet get reflected on the map.
    // RetainAll removes all entries that aren't in the provided collection
    // Effectively this is removing all entries from the map that aren't in the new list
    organizationsMap
        .keySet()
        .retainAll(orgList.stream().map(Organization::getId).collect(Collectors.toList()));

    for (Organization organization : orgList) {
      if (organizationsMap.containsKey(organization.getId())) {
        ((MutableLiveData<Organization>) requireNonNull(organizationsMap.get(organization.getId())))
            .postValue(organization);
      } else {
        organizationsMap.put(organization.getId(), new MutableLiveData<>(organization));
      }
    }

    doneChanges();
  }

  @NonNull
  public LiveData<Map<Integer, LiveData<Organization>>> getOrganizations() {
    return organizations;
  }

  @NonNull
  public LiveData<Organization> getOrganization(int organizationId)
      throws OrganizationNotFoundException {
    LiveData<Organization> organization =
        Objects.requireNonNull(organizations.getValue()).get(organizationId);

    if (organization == null) {
      throw new OrganizationNotFoundException(
          "There's no organization with the given id of " + organizationId);
    }

    return organization;
  }

  public boolean zeroOrganizations() {
    return requireNonNull(organizations.getValue()).isEmpty();
  }

  public void setConnectedOrganization(int organizationId, boolean connected)
      throws OrganizationNotFoundException {
    // Necessary cast, since java generics are invariant
    // We wouldn't need it if they could be covariant (in kotlin, for example)
    MutableLiveData<Organization> organization =
        (MutableLiveData<Organization>) getOrganization(organizationId);

    organization.postValue(requireNonNull(organization.getValue()).withConnected(connected));
  }

  // This method needs to get called after changes to the organizations. It reloads the map
  // livedata, posting the same value it already holds, so the same map gets "emitted", but with
  // updated entries. Is this the best way to do it? No. Is it al least one *right* way to do this?
  // Also no. But is it the only way I found? Yes. Would I find something better if I had more time?
  // Also yes (I hope). Do I have this time? Unfortunately, no. Henceforth, this is what we're stuck
  // with.
  public void doneChanges() {
    organizations.postValue(organizations.getValue());
  }

  public void updatePlaces(int organizationId, @NonNull List<Place> places)
      throws OrganizationNotFoundException {
    MutableLiveData<Organization> organization =
        (MutableLiveData<Organization>) getOrganization(organizationId);

    organization.postValue(requireNonNull(organization.getValue()).withPlaces(places));
  }

  @NonNull
  public List<PlaceWithOrganization> getInsidePlaces(@NonNull Point point) {
    return requireNonNull(getOrganizations().getValue()).values().stream()
        .map(organizationLiveData -> requireNonNull(organizationLiveData.getValue()))
        .filter(Organization::isConnected)
        .flatMap(
            organization ->
                organization.getInsidePlaces(point).stream()
                    .map(placeId -> new PlaceWithOrganization(placeId, organization.getId())))
        .collect(Collectors.toList());
  }

  @Value
  public static class PlaceWithOrganization {
    public int placeId;
    public int organizationId;
  }

  @NonNull
  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }

  public void setUserOrganizationHistory(
      @NonNull List<UserOrganizationHistory> userOrganizationHistory) {

    Map<Long, LiveData<UserOrganizationHistory>> map = new TreeMap<>(Collections.reverseOrder());

    for (UserOrganizationHistory userOrgHistory : userOrganizationHistory) {
      map.put(userOrgHistory.getTimestamp(), new MutableLiveData<>(userOrgHistory));
    }

    this.userOrganizationHistory.postValue(map);
  }

  @NonNull
  public LiveData<Map<Long, LiveData<UserOrganizationHistory>>> getUserOrganizationHistory() {
    return userOrganizationHistory;
  }
}
