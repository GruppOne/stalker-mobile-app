package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@Entity(primaryKeys = {"placeId", "entryTimestamp"})
public class UserPermanence {
  int placeId;
  @NonNull
  Date entryTimestamp;
  @With Date exitTimestamp;
  boolean anonymous;
}
