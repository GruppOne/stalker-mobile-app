package com.gruppone.stalker;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@EqualsAndHashCode
@AllArgsConstructor
public class Organization {

  @Getter
  private int id;

  @Getter
  private String name;

  @Getter
  private List<Place> places;

  public Organization(@NonNull JSONObject jsonOrg) {
    try {
      id = jsonOrg.getInt("id");
      name = jsonOrg.getString("name");
      places = new ArrayList<>();

      JSONArray jsonPlaces = jsonOrg.getJSONArray("places");

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
