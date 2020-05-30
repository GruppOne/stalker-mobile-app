package tech.gruppone.stalker.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import tech.gruppone.stalker.app.R;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splashscreen);

    int secondsDelayed = 1;
    new Handler()
        .postDelayed(
            new Runnable() {
              public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
              }
            },
            secondsDelayed * 900);
  }
}
