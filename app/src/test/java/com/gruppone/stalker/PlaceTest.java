package com.gruppone.stalker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class PlaceTest {

  @Test
  public void constructor() {
    //Arrange
    JSONObject jsonPlace = mock(JSONObject.class);
    int id = 1;
    JSONArray jsonPolyline = mock(JSONArray.class);

    try {
      when(jsonPlace.getInt("id")).thenReturn(id);
      when(jsonPlace.getJSONArray("polygon")).thenReturn(jsonPolyline);

      when(jsonPolyline.length()).thenReturn(0);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    //Act
    Place sut = new Place(jsonPlace);

    //Assert
    assertEquals(id, sut.getId());
    assertEquals(new ArrayList<>(), sut.getPolyLine());
  }

  @Test
  public void isInside() {
    //Arrange
    List<Point> polyline = new ArrayList<>();
    polyline.add(new Point(0, 0));
    polyline.add(new Point(0, 1));
    polyline.add(new Point(1, 1));
    polyline.add(new Point(1, 0));

    Point point = new Point(0.5, 0.5);

    Place sut = new Place(1, polyline);

    //Act
    boolean inside = sut.isInside(point);

    //Assert
    assertTrue(inside);
  }
}
