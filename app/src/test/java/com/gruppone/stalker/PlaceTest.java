package com.gruppone.stalker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class PlaceTest {

  @Test
  public void isInside() {
    //Arrange
    List<Pair<Double, Double>> pairList = new ArrayList<>();
    pairList.add(new Pair<>(0.0, 0.0));
    pairList.add(new Pair<>(1.0, 0.0));
    pairList.add(new Pair<>(1.0, 1.0));
    pairList.add(new Pair<>(0.0, 1.0));

    Place instance = new Place(pairList);

    //Act
    Boolean firstInside = instance.isInside(new Pair<>(0.5, 0.5));
    Boolean secondInside = instance.isInside(new Pair<>(5.0, 5.0));
    Boolean thirdInside = instance.isInside(new Pair<>(0.0, 0.0));

    //Assert
    assertTrue(firstInside);
    assertFalse(secondInside);
    assertFalse(thirdInside);
  }
}
