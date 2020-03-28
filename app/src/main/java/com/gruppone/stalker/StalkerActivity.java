package com.gruppone.stalker;

import androidx.appcompat.app.AppCompatActivity;

public class StalkerActivity extends AppCompatActivity {
  void checkPermissions() {
    LocationNotifier.checkPermissions(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    checkPermissions();
  }
}
