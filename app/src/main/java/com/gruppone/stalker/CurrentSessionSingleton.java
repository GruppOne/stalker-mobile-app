package com.gruppone.stalker;

import java.util.List;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  private String loggedUser;
  private List<Organization> organizations;

  private CurrentSessionSingleton() {
    
  }

  void setOrganizations(List<Organization> orgList) {
    organizations = orgList;
    //localization entry point
  }

  boolean zeroOrganizations() {
    return organizations == null || organizations.isEmpty();
  }

  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }
}
