package tech.gruppone.stalker.app.utility.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
    entities = {UserPermanence.class},
    version = 1)
public abstract class PermanenceDatabase extends RoomDatabase {
  public abstract UserPermanenceDao userPermanenceDao();
}
