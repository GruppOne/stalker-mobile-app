package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.Objects;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.WebSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;

public class OrganizationModel {

  @NonNull
  public LiveData<Organization> getOrganization(int organizationId) {
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
            jsonObject -> {
              try {
                CurrentSessionSingleton.getInstance()
                    .setConnectedOrganization(organizationId, true);
              } catch (OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }

  public void disconnect(int organizationId) {
    WebSingleton.getInstance()
        .disconnect(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            organizationId,
            jsonObject -> {
              try {
                CurrentSessionSingleton.getInstance()
                    .setConnectedOrganization(organizationId, false);
              } catch (OrganizationNotFoundException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }
}
