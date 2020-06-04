package tech.gruppone.stalker.app.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
@Data
public class UserOrganizationHistory {

  long timestamp;;
  int placeId;
  Boolean inside;


  public UserOrganizationHistory(JSONObject organizationJson, String organizationName) {

    try {
      timestamp = organizationJson.getLong("timestamp");
      this.organizationName = organizationName;
      placeId = organizationJson.getInt("placeId");
      inside = organizationJson.getBoolean("inside");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}

