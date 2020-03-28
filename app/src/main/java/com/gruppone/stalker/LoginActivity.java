package com.gruppone.stalker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends StalkerActivity {
  LoginViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    findViewById(R.id.loginButton).setOnClickListener(
      new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          viewModel.login(((EditText) findViewById(R.id.emailEditText)).getText().toString(),
            ((EditText) findViewById(R.id.passwordEditText)).getText().toString());
        }
      });
  }
}
