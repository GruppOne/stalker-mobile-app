package tech.gruppone.stalker.app.utility;

import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.auth0.android.jwt.JWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  private MutableLiveData<User> loggedUser = new MutableLiveData<>();

  @Getter String jwt = "";

  @SuppressLint("UseSparseArrays")
  private MutableLiveData<Map<Integer, LiveData<Organization>>> organizations =
      new MutableLiveData<>(new TreeMap<>());

  private CurrentSessionSingleton() {}

  public void setUser(@NonNull User user) {
    loggedUser.postValue(user);
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
    int id = Integer.parseInt(new JWT(token).getSubject());

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
                    new User(id, email, firstName, lastName, birthDate));
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  public void setOrganizationList(@NonNull List<Organization> orgList) {
    @SuppressLint("UseSparseArrays")
    Map<Integer, LiveData<Organization>> map = new TreeMap<>();

    for (Organization organization : orgList) {
      map.put(organization.getId(), new MutableLiveData<>(organization));
    }

    organizations.postValue(map);
  }

  @NonNull
  public LiveData<Map<Integer, LiveData<Organization>>> getOrganizations() {
    return organizations;
  }

  @NonNull
  public LiveData<Organization> getOrganization(int organizationId)
      throws OrganizationNotFoundException {
    LiveData<Organization> organization =
        requireNonNull(organizations.getValue()).get(organizationId);

    if (organization == null) {
      throw new OrganizationNotFoundException(
          "There's no organization with the given id of " + organizationId);
    }

    return organization;
  }

  public boolean zeroOrganizations() {
    return requireNonNull(organizations.getValue()).isEmpty();
  }

  public void connectOrganization(int organizationId) throws OrganizationNotFoundException {
    // Necessary cast, since java generics are invariant
    // We wouldn't need it if they could be covariant (in kotlin, for example)
    MutableLiveData<Organization> organization =
        (MutableLiveData<Organization>) getOrganization(organizationId);

    organization.postValue(requireNonNull(organization.getValue()).withConnected(true));
  }

  @NonNull
  public List<Integer> getInsidePlaces(@NonNull Point point) {
    List<Integer> ret = new ArrayList<>();

    for (LiveData<Organization> organizationLiveData :
        requireNonNull(organizations.getValue()).values()) {
      Organization org = requireNonNull(organizationLiveData.getValue());
      if (org.isConnected()) {
        ret.addAll(org.getInsidePlaces(point));
      }
    }

    return ret;
  }

  @NonNull
  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }
}
