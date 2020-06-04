package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.OrganizationModel;
import tech.gruppone.stalker.app.model.fragment.ConnectedModel;

public class ConnectedViewModel extends ViewModel {
  ConnectedModel model = new ConnectedModel();

  @NonNull
  public LiveData<List<LiveData<Organization>>> getConnectedOrganizations() {
    return Transformations.map(
        model.getConnectedOrganizations(), input -> new ArrayList<>(input.values()));
  }

  public void disconnect(Iterator<Long> organizationIds) {
    OrganizationModel organizationModel = new OrganizationModel();
    organizationIds.forEachRemaining(id -> organizationModel.disconnect(id.intValue()));
  }
}
