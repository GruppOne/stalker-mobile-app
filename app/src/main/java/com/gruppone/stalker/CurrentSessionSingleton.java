package com.gruppone.stalker;

import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  private User loggedUser;
  private MutableLiveData<List<Organization>> organizations = new MutableLiveData<>();

  private CurrentSessionSingleton() {

  }

  void setOrganizations(List<Organization> orgList) {
    organizations.postValue(orgList);
    //localization entry point
  }

  boolean zeroOrganizations() {
    return organizations.getValue() == null || organizations.getValue().isEmpty();
  }

  User getLoggedUser() {
    return loggedUser;
  }

  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }
}
