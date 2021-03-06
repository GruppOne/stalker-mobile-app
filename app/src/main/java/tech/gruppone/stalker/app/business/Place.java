package tech.gruppone.stalker.app.business;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3d;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Place {

  private enum Position {
    LEFT,
    ALIGNED,
    RIGHT
  }

  @Getter private String name;
  @Getter private String address;
  @Getter private String city;
  @Getter private int id;
  @Getter private List<Point> polyLine;

  public Place(@NonNull JSONObject jsonPlace) {
    try {
      id = jsonPlace.getInt("id");
      JSONObject data = jsonPlace.getJSONObject("data");
      name = data.getString("name");

      polyLine = new ArrayList<>();

      JSONArray jsonPolyLine = data.getJSONArray("polygon");

      for (int i = 0; i < jsonPolyLine.length(); ++i) {
        JSONObject jsonPoint = jsonPolyLine.getJSONObject(i);

        polyLine.add(
            Point.buildFromDegrees(
                jsonPoint.getDouble("longitude"), jsonPoint.getDouble("latitude")));
      }
      JSONObject placeInformation = data.getJSONObject("placeInfo");
      address = placeInformation.getString("address");
      city = placeInformation.getString("city");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isInside(@NonNull Point point) {
    List<Position> positions = new ArrayList<>();

    for (int i = 0; i < polyLine.size(); ++i) {
      Point origin = polyLine.get(i);
      Point vertex = polyLine.get((i != polyLine.size() - 1) ? i + 1 : 0);

      positions.add(relativePosition(origin, vertex, point));
    }

    Position position = Position.ALIGNED;

    for (Position current : positions) {
      if (current != Position.ALIGNED) {
        if (position == Position.ALIGNED) {
          position = current;
        } else if (position != current) {
          return false;
        }
      }
    }

    return true;
  }

  @NonNull
  private static Position relativePosition(
      @NonNull Point origin, @NonNull Point vertex, @NonNull Point point) {
    Vector3d baseDirection =
        new Vector3d(vertex.getX() - origin.getX(), vertex.getY() - origin.getY(), 0);
    baseDirection.normalize();

    Vector3d trialDirection =
        new Vector3d(point.getX() - origin.getX(), point.getY() - origin.getY(), 0);
    trialDirection.normalize();

    Vector3d crossProduct = new Vector3d();
    crossProduct.cross(baseDirection, trialDirection);

    if (crossProduct.z > 0) {
      return Position.LEFT;
    } else if (crossProduct.z == 0) {
      return Position.ALIGNED;
    } else {
      // if(crossProduct.z<0)
      return Position.RIGHT;
    }
  }

  @NonNull
  public Point getCenter() {
    Point center =
        polyLine
            .parallelStream()
            .reduce(
                new Point(),
                (point1, point2) ->
                    new Point(point1.getX() + point2.getX(), point1.getY() + point2.getY()));

    return new Point(center.getX() / polyLine.size(), center.getY() / polyLine.size());
  }
}
