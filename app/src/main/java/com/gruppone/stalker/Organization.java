package com.gruppone.stalker;

import android.util.Pair;

import java.util.List;

public class Organization {
  private int id;
  private String name;
  private List<Place> places;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

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
