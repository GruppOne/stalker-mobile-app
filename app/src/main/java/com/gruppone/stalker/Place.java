package com.gruppone.stalker;

import android.util.Pair;
import java.util.List;

public class Place {

  private int id;
  private List<Pair<Double, Double>> polyLine;

  public List<Pair<Double, Double>> getPolyLine() {
    return polyLine;
  }

  public int getId() {
    return id;
  }

  //to implement
  public boolean isInside(Pair<Double, Double> point) {
    return true;
  }
}
