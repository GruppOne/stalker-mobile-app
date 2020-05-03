package tech.gruppone.stalker.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.viewmodel.LoginViewModel;

public class LoginActivity extends StalkerActivity {

  LoginViewModel viewModel;

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    findViewById(R.id.loginButton)
        .setOnClickListener(
            v -> {
              if (viewModel.login(
                  ((EditText) findViewById(R.id.emailEditText)).getText().toString(),
                  ((EditText) findViewById(R.id.passwordEditText)).getText().toString())) {
                Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                startActivity(intent);
              }
            });

    // change activity view on "Not yet registered? Sign up" click
    findViewById(R.id.goToSignUp)
        .setOnClickListener(
            v -> {
              Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
              startActivity(intent);
            });
  }
}