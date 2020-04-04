package com.gruppone.stalker;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public class MainPageModel {

  void loadOrganizations() {
    WebSingleton.getInstance().getOrganizationList(jsonArray -> {
      List<Organization> organizations = new ArrayList<>();

      try {
        for (int i = 0; i < jsonArray.length(); ++i) {
          organizations
            .add(new Organization(jsonArray.getJSONObject(i)));
        }
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }

      CurrentSessionSingleton.getInstance().setOrganizations(organizations);
    }, null);
  }

  public LiveData<List<Organization>> getOrgsLiveData() {
    return CurrentSessionSingleton.getInstance().getOrganizations();
  }
}
