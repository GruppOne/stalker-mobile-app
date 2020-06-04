package tech.gruppone.stalker.app.business;

import androidx.room.Dao;
import java.sql.Timestamp;
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


}

