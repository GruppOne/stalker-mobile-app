package tech.gruppone.stalker.app.utility.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import org.json.JSONObject;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class BarelyAuthenticatedRequest extends AuthenticatedRequest {
  public BarelyAuthenticatedRequest(
      int method,
      @NonNull String url,
      @Nullable JSONObject jsonRequest,
      @Nullable Listener<JSONObject> listener,
      @Nullable ErrorListener errorListener) {
    super(method, url, jsonRequest, listener, errorListener);
  }

  @Override
  protected boolean anonymous() {
    return CurrentSessionSingleton.getInstance().isAnonymous();
  }
}
