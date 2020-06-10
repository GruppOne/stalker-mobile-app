package tech.gruppone.stalker.app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHistory {

  @PrimaryKey(autoGenerate = true)
  int uid;

  @ColumnInfo(name = "username")
  int username;

  @ColumnInfo(name = "organization_id")
  int organizationId;

  @ColumnInfo(name = "place_id")
  int placeId;

  @ColumnInfo(name = "inside")
  Boolean inside;

  @ColumnInfo(name = "timestamp")
  String timestamp;
}
