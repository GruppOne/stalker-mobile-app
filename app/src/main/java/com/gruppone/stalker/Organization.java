package com.gruppone.stalker;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Organization {

  @Getter
  private int id;

  @Getter
  private String name;

  public Organization(Integer id, String name, List<Place> places) {
    this.id = id;
    this.name = name;
    this.places = places;
  }

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
