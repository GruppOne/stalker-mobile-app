package tech.gruppone.stalker.app.business;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class OrganizationHistory {

   int userId;
   List<UserOrganizationHistory> history;

  public OrganizationHistory( @NonNull  JSONObject jsonObject) {

    history = new ArrayList<>();
    try {
      userId = jsonObject.getInt("userId");
      JSONArray historyArray = jsonObject.getJSONArray("history");
      for (int i = 0; i < historyArray.length(); i++) {
        UserOrganizationHistory userOrganizationHistory =
            new UserOrganizationHistory(historyArray.getJSONObject(i));
        history.add(userOrganizationHistory);
      }
    } catch (JSONException e) {
      new RuntimeException();
    }
  }
}

