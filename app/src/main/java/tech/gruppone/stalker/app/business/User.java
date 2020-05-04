package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

// Intellij (or maybe java) suggests that for code generation we should use the jetbrains
// version of NonNull. Point is, we don't want code generation. We want compilation time
// checking. And that's why we use the androidx NonNull
@SuppressWarnings("NullableProblems")
@Value
public class User {

  @NonFinal @Setter int id;

  @NonNull String email;
  @NonNull String firstName;
  @NonNull String lastName;
  @NonNull String birthDate;
}
