package tech.gruppone.stalker.app.viewmodel;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import tech.gruppone.stalker.app.model.LoginModel;

public class LoginViewModel extends ViewModel {

  private LoginModel model = new LoginModel();

  public void login(@NonNull String email, @NonNull String password) {
    model.login(email, password);
  }
}
