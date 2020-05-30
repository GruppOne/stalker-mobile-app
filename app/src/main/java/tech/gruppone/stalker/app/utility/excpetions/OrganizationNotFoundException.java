package tech.gruppone.stalker.app.utility.excpetions;

import androidx.annotation.Nullable;

public class OrganizationNotFoundException extends Exception {
  public OrganizationNotFoundException(@Nullable String errorMessage) {
    super(errorMessage);
  }
}
