package tech.gruppone.stalker.app.utility.location;

import static java.util.Objects.requireNonNull;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import tech.gruppone.stalker.app.business.Point;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton.PlaceWithOrganization;
import tech.gruppone.stalker.app.utility.room.PermanenceDatabase;
import tech.gruppone.stalker.app.utility.room.PersistenceSingleton;
import tech.gruppone.stalker.app.utility.room.UserPermanence;
import tech.gruppone.stalker.app.utility.web.WebSingleton;

public class LocationNotifier extends JobIntentService {

  static int JOB_ID = 1000;

  public static void enqueue(@NonNull Context ctx, @NonNull Intent work) {
    enqueueWork(ctx, LocationNotifier.class, JOB_ID, work);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    Location location = intent.getParcelableExtra("tech.gruppone.stalker.app.lastLocation");

    // This is a taccone. Whatever.
    if (location == null) {
      return;
    }

    Point point = Point.buildFromDegrees(location.getLongitude(), location.getLatitude());

    int userId =
        requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue()).getId();
    List<PlaceWithOrganization> insidePlaces =
        CurrentSessionSingleton.getInstance().getInsidePlaces(point);
    boolean anonymous = CurrentSessionSingleton.getInstance().isAnonymous();
    PermanenceDatabase database = PersistenceSingleton.getInstance().getDatabase();

    for (PlaceWithOrganization placeWithOrganization : insidePlaces) {
      database
          .userPermanenceDao()
          .insert(
              UserPermanence.builder()
                  .anonymous(anonymous)
                  .entryTimestamp(new Date())
                  .placeId(placeWithOrganization.placeId)
                  .organizationId(placeWithOrganization.organizationId)
                  .build());
    }

    List<Integer> placeIds =
        insidePlaces.stream()
            .map(placeWithOrganization -> placeWithOrganization.placeId)
            .collect(Collectors.toList());

    WebSingleton.getInstance().locationUpdate(userId, placeIds, true, anonymous);

    List<Integer> outsidePlaces = new ArrayList<>();

    for (UserPermanence userPermanence : database.userPermanenceDao().wasInside(placeIds)) {
      outsidePlaces.add(userPermanence.getPlaceId());
      database.userPermanenceDao().update(userPermanence.withExitTimestamp(new Date()));
    }

    WebSingleton.getInstance().locationUpdate(userId, outsidePlaces, false, anonymous);
  }
}
