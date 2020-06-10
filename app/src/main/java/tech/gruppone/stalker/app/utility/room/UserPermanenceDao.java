package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserPermanenceDao {
  @Insert
  void insert(@NonNull UserPermanence userPermanence);

  @Insert
  void update(@NonNull UserPermanence userPermanence);

  @Query("SELECT COUNT(*) FROM userpermanence WHERE placeId = :placeId AND exitTimestamp = NULL")
  int openEntries(int placeId);

  @Query(
      "SELECT placeId FROM userpermanence WHERE placeId NOT IN (:insideNow) AND exitTimestamp = NULL")
  List<Integer> wasInside(@NonNull List<Integer> insideNow);
}
