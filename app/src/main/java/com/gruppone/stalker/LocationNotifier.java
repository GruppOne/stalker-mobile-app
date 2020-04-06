package com.gruppone.stalker;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.google.android.gms.location.LocationResult;

public class LocationNotifier extends JobIntentService {

  static int JOB_ID = 1000;
  private CurrentSessionSingleton currentSession = CurrentSessionSingleton.getInstance();
  private WebSingleton web = WebSingleton.getInstance();

  public static void enqueue(Context ctx, Intent work) {
    enqueueWork(ctx, LocationNotifier.class, JOB_ID, work);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    LocationResult location = intent.getParcelableExtra("lastLocation");
    Point point = Point.buildFromDegrees(location.getLastLocation().getLongitude(),
      location.getLastLocation().getLatitude());
    web.locationUpdateInside(currentSession.getLoggedUser().getId(),
      currentSession.getInsidePlaces(point));
  }
}
