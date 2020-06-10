package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.json.JSONException;
import org.json.JSONObject;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Organization {

  @With int id;

  String name;
  String description;
  boolean isPrivate;
  @With List<Place> places;
  @With boolean connected;

  public Organization(@NonNull JSONObject jsonOrg) {
    try {
      id = jsonOrg.getInt("id");
      JSONObject data = jsonOrg.getJSONObject("data");
      name = data.getString("name");
      description = data.getString("description");
      isPrivate = data.getString("organizationType").equals("private");
      places = new ArrayList<>();
      connected = false;
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  @NonNull
  public List<Integer> getInsidePlaces(@NonNull Point point) {
    List<Integer> ret = new ArrayList<>();
    for (Place place : places) {
      if (place.isInside(point)) {
        ret.add(place.getId());
      }
    }
    return ret;
  }

  public Place getPlaceWithOrganizationId(int placeId) {
    Place place = new Place();
    for (Place p : places) {
      if (placeId == p.getId()) {
        place = p;
      }
    }
    return place;
  }
}
