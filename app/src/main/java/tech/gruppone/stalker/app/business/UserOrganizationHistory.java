package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import java.sql.Timestamp;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class UserOrganizationHistory {

  String timestamp;
  int placeId;
  Boolean inside;

  public UserOrganizationHistory(@NonNull JSONObject jsonObject){

    try {
      timestamp = jsonObject.getString("timestamp");
      placeId = jsonObject.getInt("placeId");
      inside = jsonObject.getBoolean("inside");
    } catch (JSONException e) {
      new RuntimeException();
    }
  }

}
