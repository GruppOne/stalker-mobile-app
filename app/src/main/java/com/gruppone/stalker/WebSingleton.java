package com.gruppone.stalker;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//TODO Add decorator to Requests to handle authentication (API key or actual login)
public class WebSingleton {

  private static WebSingleton instance;
  private RequestQueue requestQueue;
  private String serverUrl = "http://10.0.2.2:11111";

  private WebSingleton() {
    requestQueue = getRequestQueue();
  }

  public static synchronized WebSingleton getInstance() {
    if (instance == null) {
      instance = new WebSingleton();
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(App.getAppContext());
    }
    return requestQueue;
  }

  public <T> void addToRequestQueue(Request<T> request) {
    getRequestQueue().add(request);
  }

  public void locationUpdateInside(Integer userId, List<Integer> places) {
    String fullUrl = serverUrl + "/location/update";
    try {
      JSONObject request = new JSONObject();
      request.put("timestamp-ms", System.currentTimeMillis());
      request.put("userId", userId);
      request.put("anonymous", false);
      request.put("inside", true);
      request.put("placeIds", new JSONArray(places));
      addToRequestQueue(new JsonObjectRequest(Method.POST, fullUrl, request, null, null));
    } catch (JSONException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void locationUpdateOutside(Organization organization, Place place) {
    addToRequestQueue(new JsonObjectRequest(Method.GET, serverUrl, null, null, null));
  }

  public void getOrganizationList(Listener<JSONArray> successListener,
    ErrorListener errorListener) {
    String fullUrl = serverUrl + "/organization";
    addToRequestQueue(
      new JsonArrayRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }
}
