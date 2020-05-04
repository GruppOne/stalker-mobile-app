package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class User {

  @NonFinal @Setter int id;

  @NonNull String email;
  @NonNull String passwordHash;
  @NonNull String firstName;
  @NonNull String lastName;
  @NonNull String birthDate;
}
