package tech.gruppone.stalker.app.viewmodel;

import android.graphics.Color;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.model.OrganizationModel;

public class OrganizationViewModel extends ViewModel {
  private OrganizationModel model = new OrganizationModel();
  private Organization organization;

  public void connect() {
    model.connect(organization.getId());
  }

  public String getName() {
    return organization.getName();
  }

  public String getDescription() {
    return organization.getDescription();
  }

  public boolean isPrivate() {
    return organization.isPrivate();
  }

  public List<PolygonOptions> getPolygons() {
    List<PolygonOptions> polygonOptionsList = new ArrayList<>();

    for (Place place : organization.getPlaces()) {
      int color = getRandomColor();
      int transparentColor =
          Color.argb(50, Color.red(color), Color.green(color), Color.blue(color));
      PolygonOptions polygonOptions = new PolygonOptions();

      for (Point point : place.getPolyLine()) {
        polygonOptions
            .add(new LatLng(point.getLatitude(), point.getLongitude()))
            .strokeColor(color)
            .fillColor(transparentColor);
      }

      polygonOptionsList.add(polygonOptions);
    }

    return polygonOptionsList;
  }

  public LatLngBounds getBound() {
    LatLngBounds.Builder builder = LatLngBounds.builder();

    for (Place place : organization.getPlaces()) {
      for (Point point : place.getPolyLine()) {
        builder.include(new LatLng(point.getLatitude(), point.getLongitude()));
      }
    }
    return builder.build();
  }

  public void retrieveOrganization(int organizationId) {
    organization = model.getOrganization(organizationId);
  }

  public int getRandomColor() {
    Random rnd = new Random();
    return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
  }
}
