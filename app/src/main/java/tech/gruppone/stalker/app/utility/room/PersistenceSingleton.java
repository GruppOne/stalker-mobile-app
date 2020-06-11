package tech.gruppone.stalker.app.utility.room;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.room.Room;
import tech.gruppone.stalker.app.utility.App;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class PersistenceSingleton {
  private static PersistenceSingleton instance;
  private PermanenceDatabase database;

  private PersistenceSingleton() {}

  // This method is going to be used for the logout, when that happens we'll remove this suppression
  @SuppressWarnings("unused")
  public void resetDatabase() {
    database = null;
  }

  @NonNull
  public PermanenceDatabase getDatabase() {
    if (database == null) {
      int userId =
          requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue()).getId();
      database =
          Room.databaseBuilder(
                  App.getAppContext(),
                  PermanenceDatabase.class,
                  // If more users use the same app/smartphone, this way they hold different
                  // chronologies:
                  "permanence_database_" + userId)
              .build();
    }

    return database;
  }

  @NonNull
  public static synchronized PersistenceSingleton getInstance() {
    if (instance == null) {
      instance = new PersistenceSingleton();
    }

    return instance;
  }
}
