package tech.gruppone.stalker.app.business;

import java.util.List;
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
  Place place;


  public UserOrganizationHistory(JSONObject organizationJson, Organization organization) {

    try {
      timestamp = organizationJson.getLong("timestamp");
      this.organizationName = organization.getName();
      placeId = organizationJson.getInt("placeId");
      inside = organizationJson.getBoolean("inside");
      place = organization.getPlaceWithOrganizationId(placeId);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
