package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserPermanenceDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(@NonNull UserPermanence userPermanence);

  @Insert
  void update(@NonNull UserPermanence userPermanence);

  @Query("SELECT * FROM userpermanence WHERE placeId NOT IN (:insideNow) AND exitTimestamp = NULL")
  List<UserPermanence> wasInside(@NonNull List<Integer> insideNow);
}
