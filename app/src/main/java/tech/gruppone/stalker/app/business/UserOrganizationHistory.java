package tech.gruppone.stalker.app.business;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
@Data
@Builder
public class UserOrganizationHistory {

  long timestamp;
  int placeId;
  Place place;
  Organization organization;
  Boolean inside;


  public UserOrganizationHistory(JSONObject organizationJson, Organization organization) {

    try {
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      Date date = format.parse(organizationJson.getString("timestamp"));
      this.timestamp = date.getTime();
      this.organization = organization;
      placeId = organizationJson.getInt("placeId");
      inside = organizationJson.getBoolean("inside");
      place = organization.getPlaceWithOrganizationId(placeId);
    } catch (JSONException | ParseException e) {
      e.printStackTrace();
    }
  }
}
