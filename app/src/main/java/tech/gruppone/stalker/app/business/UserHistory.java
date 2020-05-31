package tech.gruppone.stalker.app.business;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserHistory {


  //Map<Integer, OrganizationHistory> organizationsHistory;
  int organizationId;
  List<OrganizationHistory> organizationHistoryList;

  /*public UserHistory(@NonNull JSONObject jsonObject) {

    organizationsHistory = new HashMap<>();
    try {
      JSONArray jsonOrganizationsHistory = jsonObject.getJSONArray("history");
      for (int i=0; i<jsonOrganizationsHistory.length(); i++){
          JSONObject jsonHistoryObject = jsonOrganizationsHistory.getJSONObject(i);
          int id = jsonHistoryObject.getInt("id");
          OrganizationHistory organizationJson = new OrganizationHistory(jsonHistoryObject.getJSONObject("historyPerOrganization"));
          organizationsHistory.put(id, organizationJson);

      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }*/
}
