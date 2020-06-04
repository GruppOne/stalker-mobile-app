package tech.gruppone.stalker.app.model.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class ReportModel {





  @NonNull
  public LiveData<Map<Long, LiveData<UserOrganizationHistory>>> getOrgsHistoryLiveData() {
    return CurrentSessionSingleton.getInstance().getUserOrganizationHistory();
  }




}
