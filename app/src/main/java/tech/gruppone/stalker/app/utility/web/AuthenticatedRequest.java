package tech.gruppone.stalker.app.utility.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.BuildConfig;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

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

    String jwt = anonymous() ? sessionSingleton.getAnonymousJwt() : sessionSingleton.getJwt();

    if (!jwt.equals("")) {
      headers.put("Authorization", "Bearer " + jwt);
    } else {
      headers.put("STALKER-ADMIN-API-KEY", BuildConfig.ADMIN_API_KEY);
    }

    return headers;
  }

  // We need this override because Volley natively doesn't recognize an empty response body as a
  // success, even with 204 response code.
  @Override
  protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
    try {
      String body =
          new String(
              response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

      if (body.length() == 0) {
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
      }

      return Response.success(new JSONObject(body), HttpHeaderParser.parseCacheHeaders(response));
    } catch (UnsupportedEncodingException | JSONException e) {
      return Response.error(new ParseError(e));
    }
  }

  protected boolean anonymous() {
    return false;
  }
}
