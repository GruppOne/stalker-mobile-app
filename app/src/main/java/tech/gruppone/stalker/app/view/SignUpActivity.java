package tech.gruppone.stalker.app.view;

import static java.util.Objects.requireNonNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.viewmodel.SignupViewModel;

public class SignUpActivity extends StalkerActivity {

  SignupViewModel viewModel;

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

    TextInputLayout emailInsertLayout = findViewById(R.id.emailSignupEditText);
    TextInputLayout passwordInsertLayout = findViewById(R.id.passwordSignupEditText);
    TextInputLayout firstNameInsertLayout = findViewById(R.id.firstNameEditText);
    TextInputLayout lastNameInsertLayout = findViewById(R.id.lastNameEditText);
    TextInputLayout dateInsertLayout = findViewById(R.id.dateEditText);

    EditText emailInsertEditText = requireNonNull(emailInsertLayout.getEditText());
    EditText passwordInsertEditText = requireNonNull(passwordInsertLayout.getEditText());
    EditText firstNameInsertEditText = requireNonNull(firstNameInsertLayout.getEditText());
    EditText lastNameInsertEditText = requireNonNull(lastNameInsertLayout.getEditText());
    EditText dateInsertEditText = requireNonNull(dateInsertLayout.getEditText());

    findViewById(R.id.signupButton)
        .setOnClickListener(
            v -> {
              String email = emailInsertEditText.getText().toString();
              String password = passwordInsertEditText.getText().toString();
              String firstName = firstNameInsertEditText.getText().toString();
              String lastName = lastNameInsertEditText.getText().toString();
              String birthDate = dateInsertEditText.getText().toString();

              boolean ok = true;

              if (email.isEmpty()) {
                emailInsertLayout.setErrorEnabled(true);
                emailInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              } else if (viewModel.invalidEmail(email)) {
                emailInsertLayout.setErrorEnabled(true);
                emailInsertLayout.setError(getString(R.string.invalidEmail));
                ok = false;
              }

              if (password.isEmpty()) {
                passwordInsertLayout.setErrorEnabled(true);
                passwordInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              } else if (viewModel.invalidPassword(password)) {
                passwordInsertLayout.setErrorEnabled(true);
                passwordInsertLayout.setError(getString(R.string.invalidPassword));
                ok = false;
              }

              if (firstName.isEmpty()) {
                firstNameInsertLayout.setErrorEnabled(true);
                firstNameInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              }

              if (lastName.isEmpty()) {
                lastNameInsertLayout.setErrorEnabled(true);
                lastNameInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              }

              if (birthDate.isEmpty()) {
                dateInsertLayout.setErrorEnabled(true);
                dateInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              }

              if (ok) {
                viewModel.signup(email, password, firstName, lastName, birthDate);
              }
            });

    viewModel
        .getUserLiveData()
        .observe(
            this,
            user -> {
              if (user != null && user.isComplete()) {
                viewModel.getUserLiveData().removeObservers(SignUpActivity.this);

                Intent intent = new Intent(SignUpActivity.this, MainPageActivity.class);
                startActivity(intent);

                finish();
              }
            });

    // change activity view on "Already have an account? Login" click
    findViewById(R.id.goToLogin).setOnClickListener(v -> finish());
  }
}
