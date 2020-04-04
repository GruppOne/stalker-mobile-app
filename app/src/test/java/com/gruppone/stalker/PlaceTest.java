package com.gruppone.stalker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class PlaceTest {

  @Test
  public void isInside_insidePoint() {
    //Arrange
    final List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    final Place instance = new Place(pairList);

    //Act
    final boolean isInside = instance.isInside(new Pair<>(0.5, 0.5));

    //Assert
    assertTrue(isInside);
  }

  @Test
  public void isInside_outsidePoint() {
    //Arrange
    final List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    final Place instance = new Place(pairList);

    //Act
    final boolean isInside = instance.isInside(new Pair<>(5.0, 5.0));

    //Assert
    assertFalse(isInside);
  }

  @Test
  public void isInside_borderPoint() {
    //Arrange
    final List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    final Place instance = new Place(pairList);

    //Act
    final boolean isInside = instance.isInside(new Pair<>(0.0, 0.0));

    //Assert
    assertFalse(isInside);
  }
}
