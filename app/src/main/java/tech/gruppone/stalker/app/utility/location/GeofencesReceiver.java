package tech.gruppone.stalker.app.utility.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class GeofencesReceiver extends BroadcastReceiver {

  private final List<Integer> nearPlaceIds = new Vector<>();
  private final GooglePositionInterface googlePositionInterface = new GooglePositionInterface();

  @Override
  public void onReceive(Context context, Intent intent) {
    GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

    if (geofencingEvent.hasError()) {
      return;
    }

    int transitionType = geofencingEvent.getGeofenceTransition();

    if (transitionType == Geofence.GEOFENCE_TRANSITION_DWELL) {
      if (nearPlaceIds.isEmpty()) {
        googlePositionInterface.startLocationUpdates();
      }

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

      if (nearPlaceIds.isEmpty()) {
        googlePositionInterface.stopLocationUpdates();
      }
    }
  }
}
