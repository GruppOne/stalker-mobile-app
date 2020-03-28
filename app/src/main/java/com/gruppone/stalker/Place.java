package com.gruppone.stalker;

import android.util.Pair;

import java.util.List;

public class Place {
  private List<Pair<Double, Double>> polyLine;

  public List<Pair<Double, Double>> GetPolyLine() {
    return polyLine;
  }

  //to implement
  public boolean isInside(Pair<Double, Double> point) {
    return true;
  }
}
