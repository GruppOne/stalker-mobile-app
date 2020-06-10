package tech.gruppone.stalker.app.utility.room;

import androidx.room.Room;
import lombok.Getter;
import tech.gruppone.stalker.app.utility.App;

public class PersistenceSingleton {
  private static PersistenceSingleton instance;
  @Getter private final PermanenceDatabase database;

  private PersistenceSingleton() {
    database =
        Room.databaseBuilder(App.getAppContext(), PermanenceDatabase.class, "permanence_database")
            .build();
  }

  public synchronized PersistenceSingleton getInstance() {
    if (instance == null) {
      instance = new PersistenceSingleton();
    }

    return instance;
  }
}
