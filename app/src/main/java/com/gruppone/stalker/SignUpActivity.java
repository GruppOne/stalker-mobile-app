package com.gruppone.stalker;

  import android.view.View;
  import android.view.View.OnClickListener;
  import android.widget.Button;
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

    goToLogin();
  }

  private void goToLogin(){
    Button login = (Button) findViewById(R.id.goToLogin);
    login.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}
