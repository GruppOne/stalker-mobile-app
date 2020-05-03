package tech.gruppone.stalker.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import tech.gruppone.stalker.app.Organization;
import tech.gruppone.stalker.app.Place;
import tech.gruppone.stalker.app.Point;

public class OrganizationTest {

  @Test
  public void constructor() {
    // Arrange
    JSONObject jsonOrg = mock(JSONObject.class);
    int id = 1;
    String name = "unipd";
    JSONArray jsonPlaces = mock(JSONArray.class);

    try {
      when(jsonOrg.getInt("id")).thenReturn(id);
      when(jsonOrg.getString("name")).thenReturn(name);
      when(jsonOrg.getJSONArray("places")).thenReturn(jsonPlaces);

      when(jsonPlaces.length()).thenReturn(0);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    // Act
    Organization sut = new Organization(jsonOrg);

    // Assert
    assertEquals(id, sut.getId());
    assertEquals(name, sut.getName());
    assertEquals(new ArrayList<Place>(), sut.getPlaces());
  }

  @Test
  public void getInsidePlaces() {
    // Arrange
    Point point = new Point(0.5, 0.5);
    List<Place> places = new ArrayList<>();
    Place place1 = mock(Place.class);
    when(place1.isInside(point)).thenReturn(true);
    when(place1.getId()).thenReturn(1);
    places.add(place1);

    Place place2 = mock(Place.class);
    when(place2.isInside(point)).thenReturn(false);
    when(place2.getId()).thenReturn(2);
    places.add(place2);

    Organization sut = new Organization(1, "test", places);

    // Act
    List<Integer> insidePlaces = sut.getInsidePlaces(point);

    // Assert
    List<Integer> expected = new ArrayList<>();
    expected.add(1);

    assertEquals(expected, insidePlaces);
  }
}
