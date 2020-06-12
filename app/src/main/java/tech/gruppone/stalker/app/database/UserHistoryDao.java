package tech.gruppone.stalker.app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.Date;
import java.util.List;

@Dao
public interface UserHistoryDao {

  @Insert
  void insertAll(UserHistory userHistory);

  @Update
  void update(UserHistory userHistory);

  @Query("DELETE FROM UserHistory")
  void deleteAll();

  @Query("SELECT COUNT(*) FROM UserHistory")
  int checkIfEmpty();
}
