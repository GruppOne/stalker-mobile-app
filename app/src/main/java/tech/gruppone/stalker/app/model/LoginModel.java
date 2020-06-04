package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import org.json.JSONException;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.model.fragment.OrganizationsModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class LoginModel {

  @NonNull
  public LiveData<User> getUserLiveData() {
    return CurrentSessionSingleton.getInstance().getLoggedUser();
  }

  public void login(@NonNull String email, @NonNull String hashedPassword) {
    WebSingleton.getInstance()
        .login(
            email,
            hashedPassword,
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

              new OrganizationsModel().loadOrganizations();
            },
            null);
  }
}
