package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import lombok.Value;
import org.json.JSONException;
import org.json.JSONObject;

@Value
public class LdapCredentials {
  @NonNull String rdn;
  @NonNull String password;

  public JSONObject toJSON() {
    JSONObject object = new JSONObject();

    try {
      object.put("rdn", rdn);
      object.put("ldapPassword", password);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return object;
  }
}
