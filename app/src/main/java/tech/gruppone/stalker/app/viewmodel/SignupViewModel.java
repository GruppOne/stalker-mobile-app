package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.model.SignupModel;

public class SignupViewModel extends ViewModel {
  SignupModel model = new SignupModel();

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
    model.signup(new User(-1, email, firstName, lastName, birthDate), hashedPassword);
  }
}
