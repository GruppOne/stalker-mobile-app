package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Value
@AllArgsConstructor
public class Organization {

  int id;

  String name;
  String description;
  boolean isPrivate;
  List<Place> places;
  @With boolean connected;

  public Organization(@NonNull JSONObject jsonOrg) {
    try {
      id = jsonOrg.getInt("id");
      JSONObject data = jsonOrg.getJSONObject("data");
      name = data.getString("name");
      description = data.getString("description");
      isPrivate = data.getBoolean("isPrivate");
      places = new ArrayList<>();
      connected=false;

      JSONArray jsonPlaces = data.getJSONArray("places");

      for (int i = 0; i < jsonPlaces.length(); ++i) {
        places.add(new Place(jsonPlaces.getJSONObject(i)));
      }
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
}
