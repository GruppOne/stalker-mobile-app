package tech.gruppone.stalker.app.view;

import static java.util.Objects.requireNonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
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

    TextInputLayout emailInsertLayout = findViewById(R.id.emailLoginEditText);
    TextInputLayout passwordInsertLayout = findViewById(R.id.passwordLoginEditText);

    EditText emailInsertEditText = requireNonNull(emailInsertLayout.getEditText());
    EditText passwordInsertEditText = requireNonNull(passwordInsertLayout.getEditText());

    emailInsertEditText.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            emailInsertLayout.setError(null);
            emailInsertLayout.setErrorEnabled(false);
          }

          @Override
          public void afterTextChanged(Editable s) {}
        });

    passwordInsertEditText.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            passwordInsertLayout.setError(null);
            passwordInsertLayout.setErrorEnabled(false);
          }

          @Override
          public void afterTextChanged(Editable s) {}
        });

    findViewById(R.id.loginButton)
        .setOnClickListener(
            v -> {
              String email = emailInsertEditText.getText().toString();
              String password = passwordInsertEditText.getText().toString();

              boolean ok = true;

              if (email.isEmpty()) {
                emailInsertLayout.setErrorEnabled(true);
                emailInsertLayout.setError(getString(R.string.emptyEmail));
                ok = false;
              } else if (viewModel.invalidEmail(email)) {
                emailInsertLayout.setErrorEnabled(true);
                emailInsertLayout.setError(getString(R.string.invalidEmail));
                ok = false;
              }

              if (password.isEmpty()) {
                passwordInsertLayout.setErrorEnabled(true);
                passwordInsertLayout.setError(getString(R.string.emptyPassword));
                ok = false;
              }

              if (ok) {
                startSpin();
                viewModel.login(email, password);
              }
            });

    viewModel
        .getUserLiveData()
        .observe(
            this,
            user -> {
              if (user != null && user.isComplete()) {
                viewModel.getUserLiveData().removeObservers(LoginActivity.this);
                stopSpin();

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

  private void startSpin() {
    findViewById(R.id.fadeBackground).setVisibility(View.VISIBLE);
    findViewById(R.id.fadeBackground).animate().alpha(0.3f);
    findViewById(R.id.loading_spinner).animate();
    findViewById(R.id.loginButton).setEnabled(false);
    findViewById(R.id.emailLoginEditText).setEnabled(false);
    findViewById(R.id.passwordLoginEditText).setEnabled(false);

    new Handler().postDelayed(() -> stopSpin(), 5000);
  }

  private void stopSpin() {
    findViewById(R.id.fadeBackground).animate().alpha(0).withEndAction(this::hideFadeBackground);
    findViewById(R.id.loading_spinner).animate().cancel();
    findViewById(R.id.loginButton).setEnabled(true);
    findViewById(R.id.emailLoginEditText).setEnabled(true);
    findViewById(R.id.passwordLoginEditText).setEnabled(true);
  }

  private void hideFadeBackground() {
    findViewById(R.id.fadeBackground).setVisibility(View.GONE);
  }
}
