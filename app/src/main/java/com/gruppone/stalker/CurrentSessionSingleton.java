package com.gruppone.stalker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CurrentSessionSingleton {
  private static CurrentSessionSingleton instance;
  private static Context ctx;

  private String loggedUser;
  private List<Organization> organizations;

  private CurrentSessionSingleton(Context context) {
    ctx = context.getApplicationContext();
  }

  void SetOrganizations(List<Organization> orgList) {
    organizations = orgList;
    //localization entry point
  }

  public static synchronized CurrentSessionSingleton getInstance(Context context) {
    if (instance == null) {
      instance = new CurrentSessionSingleton(context);
    }
    return instance;
  }
}
