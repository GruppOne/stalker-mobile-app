package com.gruppone.stalker;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

  private LoginModel model = new LoginModel();

  public Boolean login(String email, String password) {
    return model.login(email, password);
  }
}
