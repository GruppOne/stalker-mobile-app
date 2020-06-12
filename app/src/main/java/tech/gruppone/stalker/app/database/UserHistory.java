package tech.gruppone.stalker.app.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import java.util.Date;
import lombok.Builder;
import lombok.Value;

@Entity
@Value
@Builder
public class UserHistory {
  int placeId;
  @NonNull Date entryTimestamp;
  @Nullable Date exitTimestamp;
  boolean anonymous;

  public UserHistory(int placeId, @NonNull Date entryTimestamp,
    @Nullable Date exitTimestamp, boolean anonymous) {
    this.placeId = placeId;
    this.entryTimestamp = entryTimestamp;
    this.exitTimestamp = exitTimestamp;
    this.anonymous = anonymous;
  }
}
