package tech.gruppone.stalker.app.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;
import tech.gruppone.stalker.app.utility.WebSingleton;

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
  public void constructor_jsonPlaces_notNull() {
    // Arrange
    JSONObject jsonOrg = mock(JSONObject.class);
    int id = 1;
    String name = "unipd";
    JSONArray jsonPlaces = mock(JSONArray.class);
    JSONObject jsonPlace = mock(JSONObject.class);
    JSONArray jsonPolyline = Mockito.mock(JSONArray.class);
    JSONObject jsonPoint = Mockito.mock(JSONObject.class);

    try {
      when(jsonOrg.getInt("id")).thenReturn(id);
      when(jsonOrg.getString("name")).thenReturn(name);
      when(jsonOrg.getJSONArray("places")).thenReturn(jsonPlaces);

      when(jsonPlaces.length()).thenReturn(1);
      when(jsonPlaces.getJSONObject(0)).thenReturn(jsonPlace);

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
    Organization sut = new Organization(jsonOrg);

    // Assert
    assertEquals(id, sut.getId());
    assertEquals(name, sut.getName());
    assertNotEquals(new ArrayList<Place>(), sut.getPlaces());
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
