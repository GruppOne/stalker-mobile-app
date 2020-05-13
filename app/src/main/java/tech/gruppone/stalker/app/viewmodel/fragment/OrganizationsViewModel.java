package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.fragment.OrganizationsModel;

public class OrganizationsViewModel extends ViewModel {

  private OrganizationsModel model = new OrganizationsModel();

  public void loadOrganizations() {
    model.loadOrganizations();
  }

  @NonNull
  public LiveData<List<Organization>> getOrgsLiveData() {
    return model.getOrgsLiveData();
  }
}
