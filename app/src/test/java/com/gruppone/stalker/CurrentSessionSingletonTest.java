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
  public void zeroOrganizations_emptyOrganizationArray() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    //Act
    instance.setOrganizations(new ArrayList<>());

    //Assert
    assertTrue(instance.zeroOrganizations());
  }

  @Test
  public void zeroOrganizations_nullOrganizationArray() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    //Act
    instance.setOrganizations(null);

    //Assert
    assertTrue(instance.zeroOrganizations());
  }

  @Test
  public void zeroOrganizations_notEmptyOrganizationArray() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();
    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(new Organization(1, "test", null));

    //Act
    instance.setOrganizations(organizationList);

    //Assert
    assertFalse(instance.zeroOrganizations());
  }

  @Test
  public void getInsideId_insidePoint() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    final List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    final List<Place> placeList = new ArrayList<>();
    placeList.add(new Place(pairList));

    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(new Organization(1, "test", placeList));

    instance.setOrganizations(organizationList);

    //Act
    final List<Integer> insideList = instance.getInsideId(new Pair<>(0.5, 0.5));

    //Assert
    assertNotEquals(null, insideList);
    assertNotEquals(0, insideList.size());
  }

  @Test
  public void getInsideId_nullOrganizationList() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    //Act
    final List<Integer> insideList = instance.getInsideId(new Pair<>(0.5, 0.5));

    //Assert
    assertNotEquals(null, insideList);
    assertEquals(0, insideList.size());
  }

  @Test
  public void getInsideId_outsidePoint() {
    //Arrange
    final CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    final List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    final List<Place> placeList = new ArrayList<>();
    placeList.add(new Place(pairList));

    final List<Organization> organizationList = new ArrayList<>();
    organizationList.add(new Organization(1, "test", placeList));

    instance.setOrganizations(organizationList);

    //Act
    final List<Integer> insideList = instance.getInsideId(new Pair<>(5.0, 5.0));

    //Assert
    assertNotEquals(null, insideList);
    assertEquals(0, insideList.size());
  }

  @Test
  public void getInstance_notNull() {
    //Arrange
    CurrentSessionSingleton instance = CurrentSessionSingleton.getInstance();

    //Act

    //Assert
    assertNotEquals(null, instance);
  }

  @Test
  public void getInstance_instanceEqual() {
    //Arrange
    CurrentSessionSingleton first = CurrentSessionSingleton.getInstance();
    CurrentSessionSingleton second = CurrentSessionSingleton.getInstance();

    //Act

    //Assert
    assertEquals(first, second);
  }
}
