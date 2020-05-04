package tech.gruppone.stalker.app.model;

import androidx.annotation.NonNull;
import com.android.volley.Response.Listener;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.utility.WebSingleton;

public class SignupModel {
  public void signup(@NonNull User user, @NonNull String passwordHash) {
    WebSingleton.getInstance().signup(user, passwordHash, new Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject jsonObject) {
        //TODO implement this
      }
    }, null);
  }
}
