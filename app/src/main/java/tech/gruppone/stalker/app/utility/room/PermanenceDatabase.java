package tech.gruppone.stalker.app.utility.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
    entities = {UserPermanence.class},
    version = 1,
    exportSchema = false)
@TypeConverters({tech.gruppone.stalker.app.utility.room.TypeConverters.class})
public abstract class PermanenceDatabase extends RoomDatabase {
  public abstract UserPermanenceDao userPermanenceDao();
}
