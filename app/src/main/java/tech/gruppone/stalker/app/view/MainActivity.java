package tech.gruppone.stalker.app.view;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;

public class MainActivity extends StalkerActivity {

  private Location currentLocation;

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    int secondsDelayed = 1;
    new Handler()
        .postDelayed(
            new Runnable() {
              public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
              }
            },
            secondsDelayed * 900);
  }
}
