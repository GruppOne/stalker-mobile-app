package tech.gruppone.stalker.app.utility;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.auth0.android.jwt.JWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  private MutableLiveData<List<Organization>> organizations =
      new MutableLiveData<>(new ArrayList<>());

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
    organizations.postValue(orgList);
  }

  @NonNull
  public LiveData<List<Organization>> getOrganizations() {
    return organizations;
  }

  @NonNull
  public Organization getOrganization(int organizationId) throws OrganizationNotFoundException {
    for (Organization organization : Objects.requireNonNull(getOrganizations().getValue())) {
      if (organization.getId() == organizationId) {
        return organization;
      }
    }

    throw new OrganizationNotFoundException("Missing organization with id: " + organizationId);
  }

  public boolean zeroOrganizations() {
    return organizations.getValue() == null || organizations.getValue().isEmpty();
  }

  public void connectOrganization(int organizationId) {
    for (Organization organization : Objects.requireNonNull(organizations.getValue())) {
      if (organization.getId() == organizationId) {
        organization.setConnected(true);

        break;
      }
    }
  }

  @NonNull
  public List<Integer> getInsidePlaces(@NonNull Point point) {
    List<Integer> ret = new ArrayList<>();
    if (!zeroOrganizations()) {
      // organizations.getValue() could be null, but the !zeroOrganizations() check ensures it's
      // not,
      // so I silenced the linter warning
      //noinspection ConstantConditions
      for (Organization org : organizations.getValue()) {
        if (org.isConnected()) {
          ret.addAll(org.getInsidePlaces(point));
        }
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
