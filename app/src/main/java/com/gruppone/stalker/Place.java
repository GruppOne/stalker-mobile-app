package com.gruppone.stalker;

import android.util.Pair;
import java.util.List;
import lombok.Getter;

public class Place {

  @Getter
  private int id;

  @Getter
  private List<Pair<Double, Double>> polyLine;

  //to implement
  public boolean isInside(Pair<Double, Double> point) {
    return true;
  }
}
