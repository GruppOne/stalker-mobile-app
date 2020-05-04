package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  private MutableLiveData<User> loggedUser = new MutableLiveData<>();

  @Getter @Setter String jwt = "";

  private MutableLiveData<List<Organization>> organizations = new MutableLiveData<>();

  private CurrentSessionSingleton() {}

  public void setUser(@NonNull User user) {
    loggedUser.postValue(user);
  }

  public LiveData<User> getLoggedUser() {
    return loggedUser;
  }

  public void setOrganizationList(@NonNull List<Organization> orgList) {
    organizations.postValue(orgList);
  }

  @NonNull
  public LiveData<List<Organization>> getOrganizations() {
    return organizations;
  }

  public boolean zeroOrganizations() {
    return organizations.getValue() == null || organizations.getValue().isEmpty();
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
