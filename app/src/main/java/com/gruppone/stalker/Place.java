package com.gruppone.stalker;

import android.util.Pair;
import java.util.List;
import lombok.Getter;

public class Place {

  @Getter
  private int id;

  @Getter
  private List<Pair<Double, Double>> polyLine;
  
  public Place(List<Pair<Double, Double>> polyLine) {
    this.polyLine = polyLine;
  }

  public boolean isInside(Pair<Double, Double> point) {
    double xMin = Double.MAX_VALUE;
    double xMax = Double.MIN_VALUE;
    double yMin = Double.MAX_VALUE;
    double yMax = Double.MIN_VALUE;

    for (Pair<Double, Double> polyPoint : polyLine) {
      if (polyPoint.first < xMin)
        xMin = polyPoint.first;
      if (polyPoint.first > xMax)
        xMax = polyPoint.first;
      if (polyPoint.second < yMin)
        yMin = polyPoint.second;
      if (polyPoint.second > yMax)
        yMax = polyPoint.second;
    }

    return (point.first > xMin) && (point.first < xMax) && (point.second > yMin) && (point.second < yMax);
  }
}
