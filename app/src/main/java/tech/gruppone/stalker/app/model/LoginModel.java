package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.json.JSONException;
import tech.gruppone.stalker.app.business.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.WebSingleton;

public class LoginModel {

  public void login(@NonNull String email, @NonNull String hashedPassword) {
        WebSingleton.getInstance()
        .login(
            email,
            hashedPassword,
            jsonObject -> {
              try {
                String token = jsonObject.getString("jwt");

                CurrentSessionSingleton.getInstance().setJwt(token);
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }
}
