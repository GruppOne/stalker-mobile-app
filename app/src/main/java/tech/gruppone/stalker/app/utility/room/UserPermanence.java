package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Entity(primaryKeys = {"placeId", "entryTimestamp"})
public class UserPermanence {
  int placeId;
  @NonNull Timestamp entryTimestamp;
  Timestamp exitTimestamp;
  boolean anonymous;
}
