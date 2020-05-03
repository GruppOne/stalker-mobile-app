package tech.gruppone.stalker.app;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

  private LoginModel model = new LoginModel();

  // XXX needs to return void, because of all the callback stuff
  @SuppressLint("UnknownNullness")
  public Boolean login(@NonNull String email, @NonNull String password) {
    return model.login(email, password);
  }
}
