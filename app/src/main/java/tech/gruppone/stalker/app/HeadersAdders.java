package tech.gruppone.stalker.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;

// XXX Since we now know that all request and response bodies are always JSON objects
//    and never arrays, all this ordeal with a separate interface can be resolved
//    by just using a Decorator pattern on the JsonObjectRequest type
public abstract class HeadersAdders {

  @NonNull
  static Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("STALKER-ADMIN-API-KEY", BuildConfig.ADMIN_API_KEY);

    // TODO add jwt token as alternative to api key
    // headers.put("TOKEN", CurrentSessionSingleton.getToken();

    return headers;
  }

  @NonNull
  public static JsonArrayRequest buildArrReqWithHeaders(
      int method,
      @NonNull String url,
      @Nullable org.json.JSONArray jsonRequest,
      @Nullable com.android.volley.Response.Listener<org.json.JSONArray> listener,
      @Nullable com.android.volley.Response.ErrorListener errorListener) {
    return new JsonArrayRequest(method, url, jsonRequest, listener, errorListener) {
      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = HeadersAdders.getHeaders();
        map.putAll(super.getHeaders());
        return map;
      }
    };
  }

  @NonNull
  public static JsonObjectRequest buildObjReqWithHeaders(
      int method,
      @NonNull String url,
      @Nullable org.json.JSONObject jsonRequest,
      @Nullable com.android.volley.Response.Listener<org.json.JSONObject> listener,
      @Nullable com.android.volley.Response.ErrorListener errorListener) {
    return new JsonObjectRequest(method, url, jsonRequest, listener, errorListener) {
      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = HeadersAdders.getHeaders();
        map.putAll(super.getHeaders());
        return map;
      }
    };
  }
}
