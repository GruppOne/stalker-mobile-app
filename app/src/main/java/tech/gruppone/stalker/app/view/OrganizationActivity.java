package tech.gruppone.stalker.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.viewmodel.OrganizationViewModel;

public class OrganizationActivity extends FragmentActivity implements OnMapReadyCallback {

  private OrganizationViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_organization);

    viewModel = new ViewModelProvider(this).get(OrganizationViewModel.class);

    Intent startingIntent = getIntent();

    if (startingIntent.hasExtra("organizationId")) {
      viewModel.retrieveOrganization(startingIntent.getIntExtra("organizationId", 0));
    }

    ((TextView) findViewById(R.id.organizationName)).setText(viewModel.getName());
    ((TextView) findViewById(R.id.organizationDescription)).setText(viewModel.getDescription());

    ((TextView) findViewById(R.id.organizationPrivatePublicIcon))
        .setCompoundDrawablesRelativeWithIntrinsicBounds(
            viewModel.isPrivate()
                ? R.drawable.ic_lock_black_24dp
                : R.drawable.ic_lock_open_black_24dp,
            0,
            0,
            0);

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment =
        Objects.requireNonNull(
            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
    mapFragment.getMapAsync(this);

    findViewById(R.id.connectButton).setOnClickListener(v -> viewModel.connect());
  }

  /**
   * Manipulates the map once available. This callback is triggered when the map is ready to be
   * used. This is where we can add markers or lines, add listeners or move the camera. In this
   * case, we just add a marker near Sydney, Australia. If Google Play services is not installed on
   * the device, the user will be prompted to install it inside the SupportMapFragment. This method
   * will only be triggered once the user has installed Google Play services and returned to the
   * app.
   */
  @Override
  public void onMapReady(@NonNull GoogleMap googleMap) {

    for (PolygonOptions polygonOptions : viewModel.getPolygons()) {
      // TODO find a way to attach the actual Place object to the polygon in the tag (setTag()).
      //      This way, we can use the tag for fast retrieval of the place info, and we can
      //      display them in a dialog or something
      Polygon polygon = googleMap.addPolygon(polygonOptions);
    }

    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(viewModel.getBound(), 1000, 2000, 50));
  }
}
