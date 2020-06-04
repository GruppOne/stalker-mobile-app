package tech.gruppone.stalker.app.utility.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class LocationNotifier extends JobIntentService {

  static int JOB_ID = 1000;
  private CurrentSessionSingleton currentSession = CurrentSessionSingleton.getInstance();
  private WebSingleton web = WebSingleton.getInstance();

  public static void enqueue(@NonNull Context ctx, @NonNull Intent work) {
    enqueueWork(ctx, LocationNotifier.class, JOB_ID, work);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    Location location = intent.getParcelableExtra("lastLocation");
    Point point = Point.buildFromDegrees(location.getLongitude(), location.getLatitude());

    // getValue() can return null, so there's a warning that getId() could throw NullPtr,
    // but the location updates only start *after* a successful login, and the login sets the user.
    // After being set, nothing changes it, so it can never be null
    //noinspection ConstantConditions
    web.locationUpdateInside(
        currentSession.getLoggedUser().getValue().getId(), currentSession.getInsidePlaces(point));
  }
}
