package tech.gruppone.stalker.app.utility.location;

import static java.util.Objects.requireNonNull;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import tech.gruppone.stalker.app.business.Place;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.model.fragment.ConnectedModel;
import tech.gruppone.stalker.app.utility.App;

public class GeofenceHandler {

  private final List<Geofence> geofences = new Vector<>();
  private final GeofencingClient geofencingClient;

  private PendingIntent pendingIntent = null;

  private static final float RANGE_IN_METERS = 10000;

  public GeofenceHandler(@NonNull Activity activity) {
    geofencingClient = LocationServices.getGeofencingClient(activity);

    new ConnectedModel()
        .getConnectedOrganizations()
        .observeForever(
            updatedConnected -> {
              List<Integer> oldIds =
                  geofences
                      .parallelStream()
                      .map(Geofence::getRequestId)
                      .map(Integer::parseInt)
                      .collect(Collectors.toList());

              List<Place> connectedPlaces =
                  updatedConnected
                      .values()
                      .parallelStream()
                      .map(organizationLiveData -> requireNonNull(organizationLiveData.getValue()))
                      .flatMap(organization -> organization.getPlaces().parallelStream())
                      .collect(Collectors.toList());

              // With this the only remaining ids are the places that aren't connected anymore
              oldIds.removeAll(
                  connectedPlaces.parallelStream().map(Place::getId).collect(Collectors.toList()));

              if (!oldIds.isEmpty()) {
                stopGeofences(oldIds);
              }

              if (!connectedPlaces.isEmpty()) {
                addGeofences(connectedPlaces);
              }
            });
  }

  private void stopGeofences(@NonNull List<Integer> oldGeofences) {
    geofencingClient.removeGeofences(
        oldGeofences.parallelStream().map(String::valueOf).collect(Collectors.toList()));
  }

  private void addGeofences(@NonNull List<Place> places) {
    List<Integer> placeIds = places.parallelStream().map(Place::getId).collect(Collectors.toList());
    List<Point> centers =
        places.parallelStream().map(Place::getCenter).collect(Collectors.toList());

    List<Geofence> newGeofences =
        IntStream.range(0, places.size())
            .parallel()
            .mapToObj(
                i ->
                    new Geofence.Builder()
                        .setRequestId(placeIds.get(i).toString())
                        .setCircularRegion(
                            centers.get(i).getLatitude(),
                            centers.get(i).getLongitude(),
                            RANGE_IN_METERS)
                        .setTransitionTypes(
                            Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                        //.setLoiteringDelay(600)
                        .setExpirationDuration(Geofence.NEVER_EXPIRE)
                        .build())
            .collect(Collectors.toList());

    GeofencingRequest geofencingRequest =
        new GeofencingRequest.Builder()
            .addGeofences(newGeofences)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .build();

    geofencingClient
        .addGeofences(geofencingRequest, getPendingIntent())
        .addOnSuccessListener(aVoid -> geofences.addAll(newGeofences));
  }

  private PendingIntent getPendingIntent() {
    if (pendingIntent == null) {
      Intent intent = new Intent(App.getAppContext(), GeofencesReceiver.class);

      pendingIntent =
          PendingIntent.getBroadcast(
              App.getAppContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    return pendingIntent;
  }
}
