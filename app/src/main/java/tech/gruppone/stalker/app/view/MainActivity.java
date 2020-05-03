package tech.gruppone.stalker.app.view;

import android.location.Location;
import android.os.Bundle;
import tech.gruppone.stalker.R;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;

public class MainActivity extends StalkerActivity {

  private Location currentLocation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
