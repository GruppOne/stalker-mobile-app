package com.gruppone.stalker;

import android.util.Pair;
import java.util.List;
import lombok.Getter;

public class Organization {

  @Getter
  private int id;

  @Getter
  private String name;

  private List<Place> places;

  public boolean isInside(Pair<Double, Double> point) {
    boolean ret = false;
    for (Place place : places) {
      if (place.isInside(point)) {
        ret = true;
        break;
      }
    }
    return ret;
  }
}
