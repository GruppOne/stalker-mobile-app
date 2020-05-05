package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import tech.gruppone.stalker.app.model.LoginModel;

public class LoginViewModel extends ViewModel {

  private LoginModel model = new LoginModel();

  public void login(@NonNull String email, @NonNull String password) {
    String hashedPassword;

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

      hashedPassword = Arrays.toString(messageDigest.digest(password.getBytes()));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }

    model.login(email, hashedPassword);
  }
}
