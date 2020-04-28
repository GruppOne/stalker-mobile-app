package com.gruppone.stalker;

import android.os.Bundle;

public class SignUpActivity extends StalkerActivity {

  @Override
  protected void onPause() {
    super.onPause();
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    // change activity view on "Already have an account? Login" click
    findViewById(R.id.goToLogin).setOnClickListener(v -> finish());
  }
}
