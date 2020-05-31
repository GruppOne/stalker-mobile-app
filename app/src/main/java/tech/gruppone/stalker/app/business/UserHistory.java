package tech.gruppone.stalker.app.business;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Value
@AllArgsConstructor
public class UserHistory {


  Map<Integer, OrganizationHistory> organizationsHistory;

  public UserHistory(@NonNull JSONObject jsonHistory) {

    organizationsHistory = new HashMap<>();
    try {
      JSONArray jsonOrganizationsHistory = jsonHistory.getJSONArray("history");
      for (int i=0; i<jsonOrganizationsHistory.length(); i++){
          JSONObject jsonHistoryObject = jsonOrganizationsHistory.getJSONObject(i);
          int id = jsonHistoryObject.getInt("id");
          OrganizationHistory organizationJson = new OrganizationHistory(jsonHistoryObject.getJSONObject("historyPerOrganization"));
          organizationsHistory.put(id, organizationJson);

      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}
