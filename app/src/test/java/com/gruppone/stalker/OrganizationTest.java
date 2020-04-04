package com.gruppone.stalker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class OrganizationTest {

  @Test
  public void isInside() {
    //Arrange
    List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    List<Place> placeList = new ArrayList<>();
    placeList.add(new Place(pairList));

    Organization instance = new Organization(1, "test", placeList);

    //Act
    Boolean firstIsInside = instance.isInside(new Pair<>(0.5, 0.5));
    Boolean secondIsInside = instance.isInside(new Pair<>(5.0, 5.0));
    Boolean thirdIsInside = instance.isInside(new Pair<>(0.0, 1.0));

    //Assert
    assertTrue(firstIsInside);
    assertFalse(secondIsInside);
    assertFalse(thirdIsInside);
  }
}