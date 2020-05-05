package tech.gruppone.stalker.app.view;

import android.os.Bundle;
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

    findViewById(R.id.signupButton)
        .setOnClickListener(
            v -> {
              @SuppressWarnings("ConstantConditions")
              String email =
                  ((TextInputLayout) findViewById(R.id.emailSignupEditText))
                      .getEditText()
                      .getText()
                      .toString();
              @SuppressWarnings("ConstantConditions")
              String password =
                  ((TextInputLayout) findViewById(R.id.passwordSignupEditText))
                      .getEditText()
                      .getText()
                      .toString();
              @SuppressWarnings("ConstantConditions")
              String firstName =
                  ((TextInputLayout) findViewById(R.id.firstNameEditText))
                      .getEditText()
                      .getText()
                      .toString();
              @SuppressWarnings("ConstantConditions")
              String lastName =
                  ((TextInputLayout) findViewById(R.id.lastNameEditText))
                      .getEditText()
                      .getText()
                      .toString();
              @SuppressWarnings("ConstantConditions")
              String birthDate =
                  ((TextInputLayout) findViewById(R.id.dateEditText))
                      .getEditText()
                      .getText()
                      .toString();

              viewModel.signup(email, password, firstName, lastName, birthDate);
            });

    // change activity view on "Already have an account? Login" click
    findViewById(R.id.goToLogin).setOnClickListener(v -> finish());
  }
}
