package tech.gruppone.stalker.app.utility.web;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.auth0.android.jwt.JWT;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.BuildConfig;
import tech.gruppone.stalker.app.business.LdapCredentials;
import tech.gruppone.stalker.app.business.User;
import tech.gruppone.stalker.app.utility.App;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class WebSingleton {

  private static WebSingleton instance;
  private RequestQueue requestQueue;
  private final String serverUrl = BuildConfig.SERVER_URL;

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

  public void login(
      @NonNull String email,
      @NonNull String passwordHash,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/user/login";
    JSONObject requestBody = new JSONObject();

    try {
      requestBody.put("email", email);
      requestBody.put("password", passwordHash);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    addToRequestQueue(
        new JsonObjectRequest(Method.POST, fullUrl, requestBody, successListener, errorListener));
  }

  public void logout(
      int userId,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/user/" + userId + "/logout";

    addToRequestQueue(
        new JsonObjectRequest(Method.POST, fullUrl, null, successListener, errorListener));
  }

  public void signup(
      @NonNull User user,
      @NonNull String passwordHash,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/users";
    JSONObject requestBody = new JSONObject();
    JSONObject loginData = new JSONObject();
    JSONObject userData = new JSONObject();

    try {
      loginData.put("email", user.getEmail());
      loginData.put("password", passwordHash);

      userData.put("email", user.getEmail());
      userData.put("firstName", user.getFirstName());
      userData.put("lastName", user.getLastName());
      userData.put("birthDate", user.getBirthDate());

      requestBody.put("loginData", loginData);
      requestBody.put("userData", userData);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    addToRequestQueue(
        new JsonObjectRequest(Method.POST, fullUrl, requestBody, successListener, errorListener));
  }

  public void getUserInfo(
      int id,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/user/" + id;

    addToRequestQueue(
        new AuthenticatedRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }

  public void getAnonymousJwt(
      @Nullable Listener<JSONObject> successListener, @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/user/anonymous";

    addToRequestQueue(
        new AuthenticatedRequest(Method.POST, fullUrl, null, successListener, errorListener));
  }

  public void locationUpdate(
      int userId, @NonNull List<Integer> places, boolean inside, boolean anonymous) {
    String fullUrl = serverUrl + "/location/update";
    try {
      JSONObject request = new JSONObject();

      // This isn't proper, but we're going to deal with it, aren't we?
      if (anonymous) {
        userId =
            Integer.parseInt(
                requireNonNull(
                    new JWT(CurrentSessionSingleton.getInstance().getAnonymousJwt()).getSubject()));
      }

      request.put("userId", userId);
      request.put("userType", anonymous ? "anonymous" : "known");
      request.put("inside", inside);
      request.put("placeIds", new JSONArray(places));
      request.put("timestamp", new Date().getTime());
      addToRequestQueue(new BarelyAuthenticatedRequest(Method.POST, fullUrl, request, null, null));
    } catch (JSONException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void getOrganizationList(
      @Nullable Listener<JSONObject> successListener, @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/organizations";
    addToRequestQueue(
        new AuthenticatedRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }

  public void getConnectedOrganizations(
      int userId,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/user/" + userId + "/organizations/connections";
    addToRequestQueue(
        new AuthenticatedRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }

  public void getPlaces(
      int organizationId,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl = serverUrl + "/organization/" + organizationId + "/places";
    addToRequestQueue(
        new AuthenticatedRequest(Method.GET, fullUrl, null, successListener, errorListener));
  }

  public void connect(
      int userId,
      int organizationId,
      @Nullable LdapCredentials ldapCredentials,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl =
        serverUrl + "/user/" + userId + "/organization/" + organizationId + "/connection";
    // In kotlin this would have been much cuter
    // val body = ldapCredentials?.toJSON();
    JSONObject body = ldapCredentials != null ? ldapCredentials.toJSON() : null;

    addToRequestQueue(
        new AuthenticatedRequest(Method.POST, fullUrl, body, successListener, errorListener));
  }

  public void disconnect(
      int userId,
      int organizationId,
      @Nullable Listener<JSONObject> successListener,
      @Nullable ErrorListener errorListener) {
    String fullUrl =
        serverUrl + "/user/" + userId + "/organization/" + organizationId + "/connection";

    addToRequestQueue(
        new AuthenticatedRequest(Method.DELETE, fullUrl, null, successListener, errorListener));
  }
}
