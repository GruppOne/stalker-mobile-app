package tech.gruppone.stalker.app.utility.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import java.util.Date;

public class TypeConverters {
  @TypeConverter
  public Long dateToTimestamp(@Nullable Date date) {
    return date == null ? null : date.getTime();
  }

  @TypeConverter
  public Date timestampToDate(@Nullable Long timestamp) {
    return timestamp == null ? null : new Date(timestamp);
  }
}
