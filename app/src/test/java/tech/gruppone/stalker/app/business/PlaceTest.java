package tech.gruppone.stalker.app.business;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlaceTest {

  @Test
  public void constructor() {
    // Arrange
    JSONObject jsonPlace = Mockito.mock(JSONObject.class);
    int id = 1;
    JSONArray jsonPolyline = Mockito.mock(JSONArray.class);

    try {
      Mockito.when(jsonPlace.getInt("id")).thenReturn(id);
      Mockito.when(jsonPlace.getJSONArray("polygon")).thenReturn(jsonPolyline);

      Mockito.when(jsonPolyline.length()).thenReturn(0);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    // Act
    Place sut = new Place(jsonPlace);

    // Assert
    Assert.assertEquals(id, sut.getId());
    Assert.assertEquals(new ArrayList<>(), sut.getPolyLine());
  }

  @Test
  public void isInside() {
    // Arrange
    List<Point> polyline = new ArrayList<>();
    polyline.add(new Point(0, 0));
    polyline.add(new Point(0, 1));
    polyline.add(new Point(1, 1));
    polyline.add(new Point(1, 0));

    Point point = new Point(0.5, 0.5);

    Place sut = new Place(1, polyline);

    // Act
    boolean inside = sut.isInside(point);

    // Assert
    Assert.assertTrue(inside);
  }
}
