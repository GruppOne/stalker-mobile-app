package tech.gruppone.stalker.app.business;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.json.JSONException;
import org.json.JSONObject;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

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
