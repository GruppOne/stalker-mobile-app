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
import tech.gruppone.stalker.app.model.fragment.OrganizationsModel;

public class OrganizationsViewModel extends ViewModel {

  private OrganizationsModel model = new OrganizationsModel();

  public void loadOrganizations() {
    model.loadOrganizations();
  }

  @NonNull
  public LiveData<List<LiveData<Organization>>> getOrgsLiveData() {
    return Transformations.map(model.getOrgsLiveData(), input -> new ArrayList<>(input.values()));
  }

  public void connect(Iterator<Long> organizationIds) {
    OrganizationModel organizationModel = new OrganizationModel();
    organizationIds.forEachRemaining(id -> organizationModel.connect(id.intValue(), null));
  }
}
