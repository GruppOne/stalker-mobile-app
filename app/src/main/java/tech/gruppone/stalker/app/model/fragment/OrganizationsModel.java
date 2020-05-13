package tech.gruppone.stalker.app.model.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.WebSingleton;

public class OrganizationsModel {

  public void loadOrganizations() {
    WebSingleton.getInstance()
        .getOrganizationList(
            jsonObject -> {
              List<Organization> organizations = new ArrayList<>();

              try {
                JSONArray array = jsonObject.getJSONArray("organizations");

                for (int i = 0; i < array.length(); ++i) {
                  organizations.add(new Organization(array.getJSONObject(i)));
                }
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }

              CurrentSessionSingleton.getInstance().setOrganizationList(organizations);
            },
            null);
  }

  @NonNull
  public LiveData<List<Organization>> getOrgsLiveData() {
    return CurrentSessionSingleton.getInstance().getOrganizations();
  }
}
