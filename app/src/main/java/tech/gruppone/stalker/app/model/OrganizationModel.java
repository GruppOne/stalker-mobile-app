package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import java.util.Objects;
import tech.gruppone.stalker.app.business.LdapCredentials;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class OrganizationModel {

  @NonNull
  public LiveData<Organization> getOrganization(int organizationId) {
    try {
      return CurrentSessionSingleton.getInstance().getOrganization(organizationId);
    } catch (OrganizationNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void connect(int organizationId, @Nullable LdapCredentials ldapCredentials) {
    WebSingleton.getInstance()
        .connect(
            Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue())
                .getId(),
            organizationId,
            ldapCredentials,
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
