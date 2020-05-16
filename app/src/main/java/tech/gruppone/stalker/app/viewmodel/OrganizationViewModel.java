package tech.gruppone.stalker.app.viewmodel;

import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;
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
      PolygonOptions polygonOptions = new PolygonOptions();

      for (Point point : place.getPolyLine()) {
        polygonOptions.add(new LatLng(point.getLatitude(), point.getLongitude()));
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
}
