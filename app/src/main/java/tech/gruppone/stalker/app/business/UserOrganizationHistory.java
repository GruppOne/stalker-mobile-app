package tech.gruppone.stalker.app.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
@Data
@Builder
public class UserOrganizationHistory {

  long timestamp;;
  int placeId;
  Place place;
  Organization organization;
  Boolean inside;


  public UserOrganizationHistory(JSONObject organizationJson, Organization organization) {

    try {
      timestamp = organizationJson.getLong("timestamp");
      this.organization = organization;
      placeId = organizationJson.getInt("placeId");
      inside = organizationJson.getBoolean("inside");
      place = organization.getPlaceWithOrganizationId(placeId);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
