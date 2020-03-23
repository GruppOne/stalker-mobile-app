package com.gruppone.stalker;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
  private FusedLocationProviderClient locationProviderClient;
  private Location currentLocation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    locationProviderClient = LocationServices.getFusedLocationProviderClient(
      this);

    locationProviderClient.getLastLocation()
                          .addOnSuccessListener(location -> {
                            currentLocation = location;
                            Toast.makeText(MainActivity.this,
                                           location.toString(),
                                           Toast.LENGTH_LONG)
                                 .show();
                          })
                          .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                              Toast.makeText(MainActivity.this, "none",
                                             Toast.LENGTH_SHORT)
                                   .show();
                            }
                          });

    LocationRequest locationRequest = LocationRequest.create();
    locationRequest.setInterval(300000);
    locationRequest.setFastestInterval(150000);
    locationRequest.setPriority(
      LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(
      locationRequest);

    LocationServices.getSettingsClient(this)
                    .checkLocationSettings(builder.build())
                    .addOnSuccessListener(
                      new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        }
                      })
                    .addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                        if (e instanceof ResolvableApiException) {
                          try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            resolvableApiException.startResolutionForResult(
                              MainActivity.this, 1010);
                          } catch (IntentSender.SendIntentException exc) {
                            Toast.makeText(MainActivity.this, "Nouse",
                                           Toast.LENGTH_SHORT)
                                 .show();
                          }

                        }
                      }
                    });

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
                                                        MainActivity.this,
                                                        intent);
                                                    }
                                                  }, Looper.getMainLooper());
  }
}
