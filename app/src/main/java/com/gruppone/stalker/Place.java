package com.gruppone.stalker;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Place {

  @Getter
  private int id;

  @Getter
  private List<Point> polyLine;

  public boolean isInside(Point point) {
    double xMin = Double.MAX_VALUE;
    double xMax = Double.MIN_VALUE;
    double yMin = Double.MAX_VALUE;
    double yMax = Double.MIN_VALUE;

    for (Point polyPoint : polyLine) {
      if (polyPoint.getX() < xMin) {
        xMin = polyPoint.getX();
      }
      if (polyPoint.getX() > xMax) {
        xMax = polyPoint.getX();
      }
      if (polyPoint.getY() < yMin) {
        yMin = polyPoint.getY();
      }
      if (polyPoint.getY() > yMax) {
        yMax = polyPoint.getY();
      }
    }

    return (point.getX() > xMin) && (point.getX() < xMax) && (point.getY() > yMin) && (point.getY()
      < yMax);
  }
}
