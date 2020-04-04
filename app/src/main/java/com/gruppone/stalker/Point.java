package com.gruppone.stalker;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Point {


  double x, y;

  public static Point buildFromDegrees(double longitude, double latitude) {
    return new Point(xMarcator(longitude), yMarcator(latitude));
  }

  //constants and private functions to convert longitude and latitude into a cartesian system on the plane
  private static final double EARTH_EQUAT_RADIUS = 6378137.0;
  private static final double EARTH_POLAR_RADIUS = 6356752.3142;

  private static double xMarcator(double longitude) {
    return EARTH_EQUAT_RADIUS * Math.toRadians(longitude);
  }

  private static double yMarcator(double latitude) {
    //Above 89.5° or below -89.5° the projection breaks
    latitude = Math.min(Math.max(latitude, -89.5), 89.5);

    //Marcator projection:
    double earthDimensionalRateNormalized =
      1.0 - Math.pow(EARTH_POLAR_RADIUS / EARTH_EQUAT_RADIUS, 2);

    double latitudeOnEarthProj = Math.sqrt(earthDimensionalRateNormalized) *
      Math.sin(Math.toRadians(latitude));

    latitudeOnEarthProj = Math.pow(((1.0 - latitudeOnEarthProj) / (1.0 + latitudeOnEarthProj)),
      0.5 * Math.sqrt(earthDimensionalRateNormalized));

    double inputOnEarthProjNormalized =
      Math.tan(0.5 * ((Math.PI * 0.5) - Math.toRadians(latitude))) / latitudeOnEarthProj;

    return (-1) * EARTH_EQUAT_RADIUS * Math.log(inputOnEarthProjNormalized);
  }
}
