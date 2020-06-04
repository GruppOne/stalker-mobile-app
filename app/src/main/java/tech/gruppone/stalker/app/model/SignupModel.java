package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import org.json.JSONException;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class SignupModel {
  public void signup(@NonNull User user, @NonNull String passwordHash) {
    WebSingleton.getInstance()
        .signup(
            user,
            passwordHash,
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
