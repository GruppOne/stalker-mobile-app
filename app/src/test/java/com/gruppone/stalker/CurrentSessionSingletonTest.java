package com.gruppone.stalker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CurrentSessionSingletonTest {

  @Test
  public void zeroOrganizations() {
    //Arrange
    CurrentSessionSingleton first = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton second = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton third = CurrentSessionSingleton.getInstance();
    List<Organization> organizationList = new ArrayList<>();
    organizationList.add(new Organization(1, "test", null));

    //Act
    first.setOrganizations(new ArrayList<>());
    second.setOrganizations(null);
    third.setOrganizations(organizationList);

    //Assert
    assertTrue(first.zeroOrganizations());
    assertTrue(second.zeroOrganizations());
    assertFalse(third.zeroOrganizations());
  }

  @Test
  public void getInsideId() {
    //Arrange
    CurrentSessionSingleton first = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton second = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton third = CurrentSessionSingleton.getInstance();

    List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    List<Place> placeList = new ArrayList<>();
    placeList.add(new Place(pairList));

    List<Organization> organizationList = new ArrayList<>();
    organizationList.add(new Organization(1, "test", placeList));

    first.setOrganizations(organizationList);
    third.setOrganizations(organizationList);

    //Act
    List<Integer> firstinsideList = first.getInsideId(new Pair<>(0.5, 0.5));
    List<Integer> secondinsideList = second.getInsideId(new Pair<>(0.5, 0.5));
    List<Integer> thirdinsideList = third.getInsideId(new Pair<>(5.0, 5.0));

    //Assert
    assertNotEquals(null, firstinsideList);
    assertNotEquals(0, firstinsideList.size());
    assertNotEquals(null, secondinsideList);
    assertEquals(0, secondinsideList.size());
    assertEquals(0, thirdinsideList.size());
  }

  @Test
  public void getInstance() {
    //Arrange
    CurrentSessionSingleton first = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton second = CurrentSessionSingleton.getInstance();

    //Act

    //Assert
    assertNotEquals(null, first);
    assertEquals(first, second);
  }
}
