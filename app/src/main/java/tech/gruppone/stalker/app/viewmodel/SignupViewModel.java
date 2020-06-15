package tech.gruppone.stalker.app.viewmodel;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Locale;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.model.LoginModel;
import tech.gruppone.stalker.app.model.SignupModel;

public class SignupViewModel extends ViewModel {

  SignupModel model = new SignupModel();
  @SuppressWarnings("FieldCanBeLocal")
  private final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&Æ*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

  @NonNull
  public LiveData<User> getUserLiveData() {
    return new LoginModel().getUserLiveData();
  }

  public boolean invalidEmail(@NonNull String email) {
    // maybe this regex could be more restrictive
    return !email.matches(EMAIL_PATTERN);
  }

  @SuppressLint("SimpleDateFormat")
  public boolean invalidDate(@NonNull String date) {
    // LocalDate parsed = LocalDate.parse(date); //API 26

    return new SimpleDateFormat("yyyy-MM-dd").parse(date, new ParsePosition(0)) != null;
  }

  public boolean invalidPassword(@NonNull String password) {
    // between 8 and 32 characters
    return password.length() < 8
        || password.length() > 32
        // contains at least a lowercase letter
        || password.equals(password.toUpperCase(Locale.getDefault()))
        // contains at least an uppercase letter
        || password.equals(password.toLowerCase(Locale.getDefault()))
        // contains at least a number
        || !password.matches(".*\\d.*");
  }

  public void signup(
      @NonNull String email,
      @NonNull String password,
      @NonNull String firstName,
      @NonNull String lastName,
      @NonNull String birthDate) {
    String hashedPassword;

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
      messageDigest.reset();

      messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

      hashedPassword = String.format("%0128x", new BigInteger(1, messageDigest.digest()));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    model.signup(
        User.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .birthDate(birthDate)
            .build(),
        hashedPassword);
  }
}
