package tech.gruppone.stalker.app.business;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class Point implements Parcelable {

  @NonFinal double x;
  @NonFinal double y;

  @NonFinal double longitude;
  @NonFinal double latitude;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;

    this.longitude = longitudeMercator(x);
    this.latitude = latitudeMercator(y);
  }

  protected Point(@NonNull Parcel in) {
    x = in.readDouble();
    y = in.readDouble();
    longitude = in.readDouble();
    latitude = in.readDouble();
  }

  public Point() {
    x = 0;
    y = 0;
    longitude = 0;
    latitude = 0;
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
  public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeDouble(x);
    dest.writeDouble(y);
    dest.writeDouble(longitude);
    dest.writeDouble(latitude);
  }

  @NonNull
  public static Point buildFromDegrees(double longitude, double latitude) {
    Point point = new Point();

    point.x = xMercator(longitude);
    point.y = yMercator(latitude);
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

  private static double longitudeMercator(double x) {
    return Math.toDegrees(x / EARTH_EQUAT_RADIUS);
  }

  private static double yMercator(double latitude) {
    // Above 89.5° or below -89.5° the projection breaks
    latitude = Math.min(Math.max(latitude, -89.5), 89.5);

    latitude = Math.toRadians(latitude);

    return Math.log(Math.tan(latitude) + 1 / Math.cos(latitude));
  }

  private static double latitudeMercator(double y) {
    return Math.toDegrees(Math.atan(Math.sinh(y)));
  }
}
