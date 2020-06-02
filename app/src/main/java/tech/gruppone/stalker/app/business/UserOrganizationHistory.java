package tech.gruppone.stalker.app.business;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class UserOrganizationHistory {

  String timestamp;
  int placeId;
  Boolean inside;


  public UserOrganizationHistory(String timestamp, int placeId, boolean inside) {
    this.timestamp = timestamp;
    this.placeId = placeId;
    this.inside =inside;
  }
}

