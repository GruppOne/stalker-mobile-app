package tech.gruppone.stalker.app.business;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class Point implements Parcelable {

  double x;
  double y;

  // TODO implement reverse Mercator projection
  @NonFinal double longitude;
  @NonFinal double latitude;

  private Point(double x, double y) {
    this.x = x;
    this.y = y;

    // TODO implement reverse Mercator projection
    longitude = 0;
    latitude = 0;
  }

  protected Point(Parcel in) {
    x = in.readDouble();
    y = in.readDouble();
    longitude = in.readDouble();
    latitude = in.readDouble();
  }

  public static final Creator<Point> CREATOR =
      new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
          return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
          return new Point[size];
        }
      };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(x);
    dest.writeDouble(y);
    dest.writeDouble(longitude);
    dest.writeDouble(latitude);
  }

  @NonNull
  public static Point buildFromDegrees(double longitude, double latitude) {
    Point point = new Point(xMercator(longitude), yMercator(latitude));

    // TODO implement reverse Mercator projection
    point.longitude = longitude;
    point.latitude = latitude;

    return point;
  }

  // constants and private functions to convert longitude and latitude into a cartesian system on
  // the plane
  private static final double EARTH_EQUAT_RADIUS = 6378137.0;
  private static final double EARTH_POLAR_RADIUS = 6356752.3142;

  private static double xMercator(double longitude) {
    return EARTH_EQUAT_RADIUS * Math.toRadians(longitude);
  }

  private static double yMercator(double latitude) {
    // Above 89.5° or below -89.5° the projection breaks
    latitude = Math.min(Math.max(latitude, -89.5), 89.5);

    // Mercator projection:
    double earthDimensionalRateNormalized =
        1.0 - Math.pow(EARTH_POLAR_RADIUS / EARTH_EQUAT_RADIUS, 2);

    double latitudeOnEarthProj =
        Math.sqrt(earthDimensionalRateNormalized) * Math.sin(Math.toRadians(latitude));

    latitudeOnEarthProj =
        Math.pow(
            ((1.0 - latitudeOnEarthProj) / (1.0 + latitudeOnEarthProj)),
            0.5 * Math.sqrt(earthDimensionalRateNormalized));

    double inputOnEarthProjNormalized =
        Math.tan(0.5 * ((Math.PI * 0.5) - Math.toRadians(latitude))) / latitudeOnEarthProj;

    return (-1) * EARTH_EQUAT_RADIUS * Math.log(inputOnEarthProjNormalized);
  }
}
