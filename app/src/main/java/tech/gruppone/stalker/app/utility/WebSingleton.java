package tech.gruppone.stalker.app.utility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;

// TODO Add decorator to Requests to handle authentication (API key or actual login)
public class WebSingleton {

  private static WebSingleton instance;
  private RequestQueue requestQueue;
  private String serverUrl = "http://10.0.2.2:11111";

  private WebSingleton() {
    requestQueue = getRequestQueue();
  }

  @NonNull
  public static synchronized WebSingleton getInstance() {
    if (instance == null) {
      instance = new WebSingleton();
    }
    return instance;
  }

  @NonNull
  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(App.getAppContext());
    }
    return requestQueue;
  }

  public <T> void addToRequestQueue(@NonNull Request<T> request) {
    getRequestQueue().add(request);
  }

  public void locationUpdateInside(int userId, @NonNull List<Integer> places) {
    String fullUrl = serverUrl + "/location/update";
    try {
      JSONObject request = new JSONObject();

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault());

      request.put("userId", userId);
      request.put("anonymous", false);
      request.put("inside", true);
      request.put("placeIds", new JSONArray(places));
      request.put("timestampMs", format.format(new Date()));
      addToRequestQueue(new AuthenticatedRequest(Method.POST, fullUrl, request, null, null));
    } catch (JSONException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void locationUpdateOutside(@NonNull Organization organization, @NonNull Place place) {
    addToRequestQueue(new AuthenticatedRequest(Method.GET, serverUrl, null, null, null));
  }

  public void getOrganizationList(
      @Nullable Listener<JSONObject> successListener, @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/organizations";
    addToRequestQueue(
        new AuthenticatedRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }
}
