package tech.gruppone.stalker.app;

import androidx.appcompat.app.AppCompatActivity;

public class StalkerActivity extends AppCompatActivity {

  void checkPermissions() {
    GooglePositionInterface.checkPermissions(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    checkPermissions();
  }
}
