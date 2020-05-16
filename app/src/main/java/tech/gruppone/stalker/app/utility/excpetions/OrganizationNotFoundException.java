package tech.gruppone.stalker.app.utility.excpetions;

public class OrganizationNotFoundException extends Exception {
  public OrganizationNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
