package tech.gruppone.stalker.app.viewmodel;

import androidx.lifecycle.ViewModel;
import tech.gruppone.stalker.app.model.fragment.OrganizationsModel;

public class MainPageViewModel extends ViewModel {
  OrganizationsModel model = new OrganizationsModel();

  public void loadOrganizations() {
    model.loadOrganizations();
  }
}
