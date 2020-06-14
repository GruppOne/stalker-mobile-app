package tech.gruppone.stalker.app.model.fragment;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import java.util.Map;
import java.util.TreeMap;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.OrganizationModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class ConnectedModel {

  @NonNull
  public LiveData<Map<Integer, LiveData<Organization>>> getConnectedOrganizations() {
    return Transformations.map(
        CurrentSessionSingleton.getInstance().getOrganizations(),
        map -> {
          Map<Integer, LiveData<Organization>> connected = new TreeMap<>();

          for (LiveData<Organization> organizationLiveData : map.values()) {
            Organization organization = requireNonNull(organizationLiveData.getValue());
            if (organization.isConnected()) {
              connected.put(organization.getId(), organizationLiveData);
            }
          }

          return connected;
        });
  }
}
