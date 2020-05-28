package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.model.LoginModel;

public class LoginViewModel extends ViewModel {

  private LoginModel model = new LoginModel();

  @NonNull
  public LiveData<User> getUserLiveData() {
    return model.getUserLiveData();
  }

  public boolean validateEmail(@NonNull String email) {
    // maybe this regex could be more restrictive
    return email.matches("^[a-zA-Z0-9_!#$%&Æ*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
  }

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
