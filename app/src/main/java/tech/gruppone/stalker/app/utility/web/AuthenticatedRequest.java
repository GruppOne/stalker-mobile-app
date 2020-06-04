package tech.gruppone.stalker.app.utility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tech.gruppone.stalker.app.BuildConfig;

public class AuthenticatedRequest extends JsonObjectRequest {
  public AuthenticatedRequest(
      int method,
      @NonNull String url,
      @Nullable JSONObject jsonRequest,
      @Nullable Response.Listener<JSONObject> listener,
      @Nullable Response.ErrorListener errorListener) {
    super(method, url, jsonRequest, listener, errorListener);
  }

  @NonNull
  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    Map<String, String> headers = new HashMap<>(super.getHeaders());

    headers.put("Content-type", "application/json");

    CurrentSessionSingleton sessionSingleton = CurrentSessionSingleton.getInstance();

    String jwt =
        canBeAnonymous() && sessionSingleton.isAnonymous()
            ? sessionSingleton.getAnonymousJwt()
            : sessionSingleton.getJwt();

    if (!jwt.equals("")) {
      headers.put("Authorization", "Bearer " + jwt);
    } else {
      headers.put("STALKER-ADMIN-API-KEY", BuildConfig.ADMIN_API_KEY);
    }

    return headers;
  }

  protected boolean canBeAnonymous() {
    return false;
  }
}
