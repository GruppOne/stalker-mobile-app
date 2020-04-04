package com.gruppone.stalker;

import android.location.Location;
import android.os.Bundle;

public class MainActivity extends StalkerActivity {

  private Location currentLocation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
