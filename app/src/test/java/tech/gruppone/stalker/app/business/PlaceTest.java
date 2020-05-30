package tech.gruppone.stalker.app.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  public void constructor_polyLineNotNull() {
    // Arrange
    JSONObject jsonPlace = Mockito.mock(JSONObject.class);
    int id = 1;
    JSONArray jsonPolyline = Mockito.mock(JSONArray.class);
    JSONObject jsonPoint = Mockito.mock(JSONObject.class);

    try {
      Mockito.when(jsonPlace.getInt("id")).thenReturn(id);
      Mockito.when(jsonPlace.getJSONArray("polygon")).thenReturn(jsonPolyline);

      Mockito.when(jsonPolyline.length()).thenReturn(1);
      Mockito.when(jsonPolyline.getJSONObject(0)).thenReturn(jsonPoint);

      Mockito.when(jsonPoint.getDouble("longitude")).thenReturn(0.0);
      Mockito.when(jsonPoint.getDouble("latitude")).thenReturn(0.0);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    // Act
    Place sut = new Place(jsonPlace);

    // Assert
    Assert.assertEquals(id, sut.getId());
    Assert.assertNotEquals(new ArrayList<>(), sut.getPolyLine());
  }

  @Test
  public void isInside_insidePoint() {
    // Arrange
    final List<Point> pairList = new ArrayList<>();
    pairList.add(Point.buildFromDegrees(45.411222, 11.887321));
    pairList.add(Point.buildFromDegrees(45.411113, 11.887784));
    pairList.add(Point.buildFromDegrees(45.411442, 11.887945));
    pairList.add(Point.buildFromDegrees(45.411554, 11.887474));

    final Place instance = new Place(1, pairList);

    // Act
    final boolean isInside = instance.isInside(Point.buildFromDegrees(45.411329, 11.887631));

    // Assert
    assertTrue(isInside);
  }

  @Test
  public void isInside_outsidePoint() {
    // Arrange
    final List<Point> pairList = new ArrayList<>();
    pairList.add(Point.buildFromDegrees(45.411222, 11.887321));
    pairList.add(Point.buildFromDegrees(45.411113, 11.887784));
    pairList.add(Point.buildFromDegrees(45.411442, 11.887945));
    pairList.add(Point.buildFromDegrees(45.411554, 11.887474));

    final Place instance = new Place(1, pairList);

    // Act
    final boolean isInside = instance.isInside(Point.buildFromDegrees(45.411463, 11.886950));

    // Assert
    assertFalse(isInside);
  }

  @Test
  public void isInside_borderPoint() {
    // Arrange
    final List<Point> pairList = new ArrayList<>();
    pairList.add(Point.buildFromDegrees(45.411222, 11.887321));
    pairList.add(Point.buildFromDegrees(45.411113, 11.887784));
    pairList.add(Point.buildFromDegrees(45.411442, 11.887945));
    pairList.add(Point.buildFromDegrees(45.411554, 11.887474));

    final Place instance = new Place(1, pairList);

    // Act
    final boolean isInside = instance.isInside(Point.buildFromDegrees(45.411554, 11.887474));

    // Assert
    assertTrue(isInside);
  }
}
