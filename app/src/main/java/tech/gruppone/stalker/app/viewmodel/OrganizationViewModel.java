package tech.gruppone.stalker.app.viewmodel;

import static java.util.Objects.requireNonNull;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
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
  private LiveData<Organization> organization;

  public void connect() {
    model.connect(requireNonNull(organization.getValue()).getId());
  }

  public void disconnect() {
    model.disconnect(requireNonNull(organization.getValue()).getId());
  }

  @NonNull
  public LiveData<Boolean> isConnected() {
    return Transformations.map(organization, Organization::isConnected);
  }

  @NonNull
  public String getName() {
    return requireNonNull(organization.getValue()).getName();
  }

  @NonNull
  public String getDescription() {
    return requireNonNull(organization.getValue()).getDescription();
  }

  public boolean isPrivate() {
    return requireNonNull(organization.getValue()).isPrivate();
  }

  @NonNull
  public List<PolygonOptions> getPolygons() {
    List<PolygonOptions> polygonOptionsList = new ArrayList<>();

    for (Place place : requireNonNull(organization.getValue()).getPlaces()) {
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

  @NonNull
  public LatLngBounds getBound() {
    LatLngBounds.Builder builder = LatLngBounds.builder();

    for (Place place : requireNonNull(organization.getValue()).getPlaces()) {
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
