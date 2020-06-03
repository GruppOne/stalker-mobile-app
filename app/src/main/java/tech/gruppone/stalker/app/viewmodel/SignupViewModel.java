package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.model.SignupModel;

public class SignupViewModel extends ViewModel {
  SignupModel model = new SignupModel();

  public boolean invalidEmail(@NonNull String email) {
    // maybe this regex could be more restrictive
    return !email.matches("^[a-zA-Z0-9_!#$%&Æ*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
  }

  public boolean invalidPassword(@NonNull String password) {
    // between 8 and 32 characters
    return password.length() <= 8
        || password.length() >= 32
        // contains at least a lowercase letter
        || password.equals(password.toUpperCase(Locale.getDefault()))
        // contains at least an uppercase letter
        || password.equals(password.toLowerCase(Locale.getDefault()))
        // contains at least a number
        || !password.matches(".*\\d.*");
  }

  public void signup(
      @NonNull String email,
      @NonNull String password,
      @NonNull String firstName,
      @NonNull String lastName,
      @NonNull String birthDate) {
    String hashedPassword;

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

      hashedPassword = Arrays.toString(messageDigest.digest(password.getBytes()));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    model.signup(
        User.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .birthDate(birthDate)
            .build(),
        hashedPassword);
  }
}
