package tech.gruppone.stalker.app.utility.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class GeofencesReceiver extends BroadcastReceiver {

  private final List<Integer> nearPlaceIds = new Vector<>();
  private final GooglePositionInterface googlePositionInterface = new GooglePositionInterface();

  @Override
  public void onReceive(@NonNull Context context, @NonNull Intent intent) {
    GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

    if (geofencingEvent.hasError()) {
      return;
    }

    int transitionType = geofencingEvent.getGeofenceTransition();

    if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER) {
      if (nearPlaceIds.isEmpty()) {
        googlePositionInterface.startLocationUpdates();
      }

      Log.i("geofence receiver", "enter");

      nearPlaceIds.addAll(
          geofencingEvent
              .getTriggeringGeofences()
              .parallelStream()
              .map(Geofence::getRequestId)
              .map(Integer::parseInt)
              .collect(Collectors.toList()));
    } else if (transitionType == Geofence.GEOFENCE_TRANSITION_EXIT) {
      nearPlaceIds.removeAll(
          geofencingEvent
              .getTriggeringGeofences()
              .parallelStream()
              .map(Geofence::getRequestId)
              .map(Integer::parseInt)
              .collect(Collectors.toList()));

      Log.i("geofence receiver", "exit");

      if (nearPlaceIds.isEmpty()) {
        googlePositionInterface.stopLocationUpdates();
      }
    }
  }
}
