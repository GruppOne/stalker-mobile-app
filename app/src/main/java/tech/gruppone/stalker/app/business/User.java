package tech.gruppone.stalker.app.business;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

  int id;

  String email;
  String firstName;
  String lastName;
  String birthDate;

  public boolean isComplete() {
    return email != null && firstName != null && lastName != null && birthDate != null;
  }
}
