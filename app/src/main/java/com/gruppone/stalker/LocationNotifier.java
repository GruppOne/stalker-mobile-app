package com.gruppone.stalker;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.*;

public class LocationNotifier extends JobIntentService {
  static int JOB_ID = 1000;

  private static LocationRequest locationRequest;

  public static void enqueue(Context ctx, Intent work) {
    enqueueWork(ctx, LocationNotifier.class, JOB_ID, work);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setComponent(new ComponentName(this, Display.class));
    startActivity(intent);
  }

  static void startLocationUpdates(Activity activity) {
    checkPermissions(activity);

    FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(
      App.getAppContext());

    locationProviderClient.requestLocationUpdates(locationRequest,
                                                  new LocationCallback() {
                                                    @Override
                                                    public void onLocationAvailability(LocationAvailability locationAvailability) {

                                                    }

                                                    @Override
                                                    public void onLocationResult(LocationResult locationResult) {
                                                      Intent intent = new Intent();
                                                      intent.putExtra(
                                                        "lastLocation",
                                                        locationResult.getLastLocation());
                                                      LocationNotifier.enqueue(
                                                        App.getAppContext(),
                                                        intent);
                                                    }
                                                  }, Looper.getMainLooper());
  }

  static void checkPermissions(Activity activity) {
    if (locationRequest == null) {
      locationRequest = LocationRequest.create();
      locationRequest.setInterval(300000);
      locationRequest.setFastestInterval(150000);
      locationRequest.setPriority(
        LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(
      locationRequest);

    LocationServices.getSettingsClient(activity)
                    .checkLocationSettings(builder.build())
                    .addOnSuccessListener(locationSettingsResponse -> {
                    })
                    .addOnFailureListener(e -> {
                      if (e instanceof ResolvableApiException) {
                        try {
                          ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                          resolvableApiException.startResolutionForResult(
                            activity, 1010);
                        } catch (IntentSender.SendIntentException exc) {
                          Toast.makeText(activity, "Nouse", Toast.LENGTH_SHORT)
                               .show();
                        }
                      }
                    });
  }
}
