package com.gruppone.stalker;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class MainPageModel {

  void loadOrganizations() {
    WebSingleton.getInstance().getOrganizationList(jsonObject -> {
      List<Organization> organizations = new ArrayList<>();

      try {
        JSONArray array = jsonObject.getJSONArray("organizations");

        for (int i = 0; i < array.length(); ++i) {
          organizations
            .add(new Organization(array.getJSONObject(i)));
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
