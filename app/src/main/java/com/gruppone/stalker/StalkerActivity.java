package com.gruppone.stalker;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

public class StalkerActivity extends AppCompatActivity {
  void checkPermissions(Activity activity){

  }

  @Override
  protected void onResume() {
    super.onResume();
    checkPermissions(this);
  }
}
