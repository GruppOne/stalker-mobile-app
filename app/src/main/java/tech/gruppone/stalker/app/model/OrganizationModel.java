package tech.gruppone.stalker.app.model;

import java.util.Objects;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.WebSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;

public class OrganizationModel {

  public Organization getOrganization(int organizationId) {
    try {
      return CurrentSessionSingleton.getInstance().getOrganization(organizationId);
    } catch (OrganizationNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void connect(int organizationId) {
    WebSingleton.getInstance()
        .connect(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            organizationId,
            jsonObject -> CurrentSessionSingleton.getInstance().connectOrganization(organizationId),
            null);
  }
}
