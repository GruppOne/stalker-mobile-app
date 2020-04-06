package com.gruppone.stalker;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  //TODO hardcoded!
  @Getter
  private User loggedUser = new User(1);

  private MutableLiveData<List<Organization>> organizations = new MutableLiveData<>();

  private CurrentSessionSingleton() {

  }

  void setOrganizations(List<Organization> orgList) {
    organizations.postValue(orgList);
    //localization entry point
  }

  LiveData<List<Organization>> getOrganizations() {
    return organizations;
  }

  boolean zeroOrganizations() {
    return organizations.getValue() == null || organizations.getValue().isEmpty();
  }

  public List<Integer> getInsidePlaces(Point point) {
    List<Integer> ret = new ArrayList<>();
    if (!zeroOrganizations()) {
      //organizations.getValue() could be null, but the !zeroOrganizations() check ensures it's not,
      //so I silenced the linter warning
      //noinspection ConstantConditions
      for (Organization org : organizations.getValue()) {
        ret.addAll(org.getInsidePlaces(point));
      }
    }
    return ret;
  }

  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }
}
