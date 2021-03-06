package tech.gruppone.stalker.app.utility.location;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import tech.gruppone.stalker.app.utility.App;

public class GooglePositionInterface {
  private static LocationRequest locationRequest =
      LocationRequest.create()
          .setInterval(5000)
          .setExpirationDuration(500000000)
          .setFastestInterval(5000)
          .setMaxWaitTime(5000)
          .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

  private final LocationCallback locationCallback =
      new LocationCallback() {
        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {}

        @Override
        public void onLocationResult(LocationResult locationResult) {
          Intent intent = new Intent();
          intent.putExtra(
              "tech.gruppone.stalker.app.lastLocation", locationResult.getLastLocation());
          LocationNotifier.enqueue(App.getAppContext(), intent);
        }
      };

  FusedLocationProviderClient locationProviderClient =
      LocationServices.getFusedLocationProviderClient(App.getAppContext());

  public void startLocationUpdates() {
    locationProviderClient.requestLocationUpdates(
        locationRequest, locationCallback, Looper.getMainLooper());
  }

  public void stopLocationUpdates() {
    locationProviderClient.removeLocationUpdates(locationCallback);
  }

  public void checkPermissions(@NonNull Activity activity) {
    LocationSettingsRequest.Builder builder =
        new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

    LocationServices.getSettingsClient(activity)
        .checkLocationSettings(builder.build())
        .addOnSuccessListener(locationSettingsResponse -> {})
        .addOnFailureListener(
            e -> {
              if (e instanceof ResolvableApiException) {
                try {
                  ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                  resolvableApiException.startResolutionForResult(activity, 1010);
                } catch (IntentSender.SendIntentException exc) {
                  Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
              }
            });
  }
}
