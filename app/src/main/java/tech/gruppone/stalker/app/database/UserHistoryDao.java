package tech.gruppone.stalker.app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserHistoryDao {

  @Insert
  void insertAll(List<UserHistory> userHistoryList);

  @Query("DELETE FROM UserHistory")
  void deleteAll();

  @Query("SELECT COUNT(*) FROM UserHistory")
  int checkIfEmpty();
}
