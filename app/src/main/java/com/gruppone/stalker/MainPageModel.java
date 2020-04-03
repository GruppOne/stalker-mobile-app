package com.gruppone.stalker;

import androidx.lifecycle.LiveData;
import java.util.List;

public class MainPageModel {

  void loadOrganizations() {
    WebSingleton.getInstance().getOrganizationList();
  }

  public LiveData<List<Organization>> getOrgsLiveData() {
    return CurrentSessionSingleton.getInstance().getOrganizations();
  }
}
