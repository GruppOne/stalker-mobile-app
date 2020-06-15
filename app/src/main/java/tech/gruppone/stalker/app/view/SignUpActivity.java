package tech.gruppone.stalker.app.view;

import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.viewmodel.SignupViewModel;

public class SignUpActivity extends StalkerActivity {

  SignupViewModel viewModel;
  DatePickerDialog picker;

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

    TextInputLayout emailInsertLayout = findViewById(R.id.emailSignupEditText);
    TextInputLayout passwordInsertLayout = findViewById(R.id.passwordSignupEditText);
    TextInputLayout passwordConfirmLayout = findViewById(R.id.passwordConfirmSignupEditText);
    TextInputLayout firstNameInsertLayout = findViewById(R.id.firstNameEditText);
    TextInputLayout lastNameInsertLayout = findViewById(R.id.lastNameEditText);
    TextInputLayout dateInsertLayout = findViewById(R.id.dateEditText);

    defineStandardListener(emailInsertLayout);
    defineStandardListener(passwordInsertLayout);
    defineStandardListener(passwordConfirmLayout);
    defineStandardListener(firstNameInsertLayout);
    defineStandardListener(lastNameInsertLayout);
    defineStandardListener(dateInsertLayout);

    EditText emailInsertEditText = requireNonNull(emailInsertLayout.getEditText());
    EditText passwordInsertEditText = requireNonNull(passwordInsertLayout.getEditText());
    EditText passwordConfirmEditText = requireNonNull(passwordConfirmLayout.getEditText());
    EditText firstNameInsertEditText = requireNonNull(firstNameInsertLayout.getEditText());
    EditText lastNameInsertEditText = requireNonNull(lastNameInsertLayout.getEditText());
    EditText dateInsertEditText = requireNonNull(dateInsertLayout.getEditText());

    dateInsertEditText.setInputType(InputType.TYPE_NULL);
    dateInsertEditText.setOnClickListener(
        v -> {
          final Calendar cldr = Calendar.getInstance();
          int day = cldr.get(Calendar.DAY_OF_MONTH);
          int month = cldr.get(Calendar.MONTH);
          int year = cldr.get(Calendar.YEAR);
          // date picker dialog
          picker =
              new DatePickerDialog(
                  this,
                  (view, year1, monthOfYear, dayOfMonth) ->
                      dateInsertEditText.setText(
                          String.format("%04d", year1)
                              + "-"
                              + String.format("%02d", (monthOfYear + 1))
                              + "-"
                              + String.format("%02d", dayOfMonth)),
                  year,
                  month,
                  day);
          picker.show();
          picker.getDatePicker().getTouchables().get(0).performClick();
          picker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
          picker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE);
        });

    findViewById(R.id.signupButton)
        .setOnClickListener(
            v -> {
              String email = emailInsertEditText.getText().toString();
              String password = passwordInsertEditText.getText().toString();
              String confirmedPassword = passwordConfirmEditText.getText().toString();
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

              if (confirmedPassword.isEmpty()) {
                passwordConfirmLayout.setErrorEnabled(true);
                passwordConfirmLayout.setError(getString(R.string.emptyField));
                ok = false;
              } else if (!password.equals(confirmedPassword)) {
                passwordConfirmLayout.setErrorEnabled(true);
                passwordConfirmLayout.setError(getString(R.string.invalidPasswordConfirm));
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

              // TODO validate format
              if (birthDate.isEmpty()) {
                dateInsertLayout.setErrorEnabled(true);
                dateInsertLayout.setError(getString(R.string.emptyField));
                ok = false;
              }

              if (ok) {
                startSpin();
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

  private void startSpin() {
    findViewById(R.id.fadeBackground).bringToFront();
    findViewById(R.id.fadeBackground).setVisibility(View.VISIBLE);
    findViewById(R.id.fadeBackground).animate().alpha(0.3f);
    findViewById(R.id.loading_spinner).animate();
    setEnabledToPageControl(false);

    new Handler().postDelayed(this::stopSpin, 5000);
  }

  private void stopSpin() {
    findViewById(R.id.fadeBackground).animate().alpha(0).withEndAction(this::hideFadeBackground);
    findViewById(R.id.loading_spinner).animate().cancel();
    setEnabledToPageControl(true);
  }

  private void setEnabledToPageControl(boolean value) {
    findViewById(R.id.signupButton).setEnabled(value);
    findViewById(R.id.firstNameEditText).setEnabled(value);
    findViewById(R.id.lastNameEditText).setEnabled(value);
    findViewById(R.id.dateEditText).setEnabled(value);
    findViewById(R.id.emailSignupEditText).setEnabled(value);
    findViewById(R.id.passwordSignupEditText).setEnabled(value);
    findViewById(R.id.goToLogin).setEnabled(value);
  }

  private void hideFadeBackground() {
    findViewById(R.id.fadeBackground).setVisibility(View.GONE);
  }

  private void defineStandardListener(TextInputLayout layout) {
    EditText editText = requireNonNull(layout.getEditText());

    editText.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            layout.setError(null);
            layout.setErrorEnabled(false);
          }

          @Override
          public void afterTextChanged(Editable s) {}
        });
  }
}
