/*package tech.gruppone.stalker.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// implementation of singleton pattern because the creation of database is resource expensive
@Database(
    entities = {UserHistory.class},
    version = 2,
    exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserHistoryDao userHistoryDao();

  private static AppDatabase INSTANCE;

  protected AppDatabase() {};

  public static AppDatabase getInstance(final Context context) {
    if (INSTANCE == null) {
      INSTANCE =
          Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user_historyDb")
              .allowMainThreadQueries()
              .build();
    }
    return INSTANCE;
  }
}*/
