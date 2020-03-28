package com.gruppone.stalker;

import android.util.Pair;

import java.util.List;

public class Organization {
  private int id;
  private String name;
  private List<Place> places;

  public int GetId() {
    return id;
  }

  public String GetName() {
    return name;
  }

  public Boolean IsInside(Pair<Double, Double> point) {
    Boolean ret = false;
    for (Place place : places) {
      if (place.IsInside(point)) {
        ret = true;
        break;
      }
    }
    return ret;
  }
}
