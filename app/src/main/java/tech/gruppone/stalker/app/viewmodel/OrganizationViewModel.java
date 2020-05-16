package tech.gruppone.stalker.app.viewmodel;

import androidx.lifecycle.ViewModel;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.OrganizationModel;

public class OrganizationViewModel extends ViewModel {
  private OrganizationModel model = new OrganizationModel();
  private Organization organization;

  public void connect() {
    model.connect(organization.getId());
  }

  public String getName() {
    return organization.getName();
  }

  public String getDescription() {
    return organization.getDescription();
  }

  public boolean isPrivate() {
    return organization.isPrivate();
  }

  public void retrieveOrganization(int organizationId) {
    organization = model.getOrganization(organizationId);
  }
}
