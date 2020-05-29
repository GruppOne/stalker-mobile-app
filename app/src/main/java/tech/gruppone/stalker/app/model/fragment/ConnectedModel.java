package tech.gruppone.stalker.app.model.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.List;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class ConnectedModel {

  @NonNull
  public LiveData<List<Organization>> getConnectedOrganizations() {
    return Transformations.map(
        CurrentSessionSingleton.getInstance().getOrganizations(),
        list -> {
          List<Organization> connected = new ArrayList<>();

          for (Organization organization : list) {
            if (organization.isConnected()) {
              connected.add(organization);
            }
          }

          return connected;
        });
  }
}
