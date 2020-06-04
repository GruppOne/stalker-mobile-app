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

                // TODO update field name for anonymous token in json as soon as server implements
                // it
                String anonymousToken = jsonObject.getString("anonymousJwt");

                CurrentSessionSingleton.getInstance().setJwt(token, anonymousToken);
              } catch (JSONException e) {
                throw new RuntimeException(e);
              }
            },
            null);
  }
}
