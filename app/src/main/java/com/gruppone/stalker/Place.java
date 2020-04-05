package com.gruppone.stalker;

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
public class Place {

  @Getter
  private int id;

  @Getter
  private List<Point> polyLine;

  public Place(JSONObject jsonPlace) {
    try {
      id = jsonPlace.getInt("id");
      polyLine = new ArrayList<>();

      JSONArray jsonPolyLine = jsonPlace.getJSONArray("polygon");

      for (int i = 1; i < jsonPolyLine.length(); ++i) {
        JSONObject jsonPoint = jsonPolyLine.getJSONObject(i);

        polyLine.add(Point
          .buildFromDegrees(jsonPoint.getDouble("longitude"), jsonPoint.getDouble("latitude")));
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

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
