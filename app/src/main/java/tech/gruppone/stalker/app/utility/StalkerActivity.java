package tech.gruppone.stalker.app.utility;

import androidx.appcompat.app.AppCompatActivity;
import tech.gruppone.stalker.app.business.location.GooglePositionInterface;

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
