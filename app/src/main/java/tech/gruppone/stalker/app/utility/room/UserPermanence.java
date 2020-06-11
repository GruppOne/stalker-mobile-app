package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import java.util.Date;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@Entity(primaryKeys = {"placeId", "entryTimestamp"})
public class UserPermanence {
  int placeId;
  @NonNull Date entryTimestamp;
  @Nullable @With Date exitTimestamp;
  boolean anonymous;

  public UserPermanence(
      int placeId, @NonNull Date entryTimestamp, @Nullable Date exitTimestamp, boolean anonymous) {
    this.placeId = placeId;
    this.entryTimestamp = entryTimestamp;
    this.exitTimestamp = exitTimestamp;
    this.anonymous = anonymous;
  }
}
