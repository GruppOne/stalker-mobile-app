package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Organization {

  @Getter private final int id;

  @Getter private final String name;
  @Getter private final List<Place> places;
  @Getter @Setter private boolean connected = false;

  public Organization(@NonNull JSONObject jsonOrg) {
    try {
      id = jsonOrg.getInt("id");
      JSONObject data = jsonOrg.getJSONObject("data");
      name = data.getString("name");
      places = new ArrayList<>();

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
