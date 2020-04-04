package com.gruppone.stalker;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;

public class WebSingleton {

  private static WebSingleton instance;
  private RequestQueue requestQueue;
  private String serverUrl = "192.168.1.113:11111";

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

  public void userEntered(Organization organization, Place place) {
    addToRequestQueue(new JsonObjectRequest(Method.GET, serverUrl, null, null, null));
  }

  public void userExited(Organization organization, Place place) {
    addToRequestQueue(new JsonObjectRequest(Method.GET, serverUrl, null, null, null));
  }

  public void getOrganizationList(Listener<JSONArray> successListener,
    ErrorListener errorListener) {
    String fullUrl = serverUrl + "/organization";
    addToRequestQueue(
      new JsonArrayRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }
}
