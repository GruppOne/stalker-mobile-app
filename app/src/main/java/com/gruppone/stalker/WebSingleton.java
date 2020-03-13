package com.gruppone.stalker;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WebSingleton {
  private static WebSingleton instance;
  private RequestQueue requestQueue;
  private static Context ctx;

  private WebSingleton(Context context) {
    ctx = context.getApplicationContext();
    requestQueue = getRequestQueue();
  }

  public static synchronized WebSingleton getInstance(Context context) {
    if (instance == null) {
      instance = new WebSingleton(context);
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(ctx);
    }
    return requestQueue;
  }

  public <T> void addToRequestQueue(Request<T> request) {
    getRequestQueue().add(request);
  }
}
