package tech.gruppone.stalker.app.model;

import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class MainPageModel {

  public void toggleAnonymous() {
    CurrentSessionSingleton.getInstance()
        .setAnonymous(!CurrentSessionSingleton.getInstance().isAnonymous());
  }
}
