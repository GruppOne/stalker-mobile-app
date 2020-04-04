package com.gruppone.stalker;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Organization {

  @Getter
  private int id;

  @Getter
  private String name;

  private List<Place> places;

  public boolean isInside(Point point) {
    boolean ret = false;
    for (Place place : places) {
      if (place.isInside(point)) {
        ret = true;
        break;
      }
    }
    return ret;
  }
}
