package com.gruppone.stalker;

import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class HeadersAdders {

  private static Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("STALKER-ADMIN-API-KEY", BuildConfig.apikey);

    //TODO add jwt token as alternative to api key
    //headers.put("TOKEN", CurrentSessionSingleton.getToken();

    return headers;
  }

  public static JsonArrayRequest buildArrReqWithHeaders(int method,
    String url,
    @Nullable org.json.JSONArray jsonRequest,
    com.android.volley.Response.Listener<org.json.JSONArray> listener,
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

  public static JsonObjectRequest buildObjReqWithHeaders(int method,
    String url,
    @Nullable org.json.JSONObject jsonRequest,
    com.android.volley.Response.Listener<org.json.JSONObject> listener,
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
