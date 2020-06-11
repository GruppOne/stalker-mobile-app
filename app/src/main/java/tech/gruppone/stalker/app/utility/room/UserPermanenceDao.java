package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserPermanenceDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(@NonNull UserPermanence userPermanence);

  @Update
  void update(@NonNull UserPermanence userPermanence);

  @Query("SELECT * FROM userpermanence WHERE exitTimestamp IS NULL")
  @NonNull
  List<UserPermanence> isInside();

  @Query("SELECT * FROM userpermanence WHERE exitTimestamp IS NOT NULL")
  @NonNull
  List<UserPermanence> history();

  @Query("SELECT * FROM userpermanence WHERE placeId NOT IN (:insideNow) AND exitTimestamp IS NULL")
  @NonNull
  List<UserPermanence> wasInside(@NonNull List<Integer> insideNow);
}
