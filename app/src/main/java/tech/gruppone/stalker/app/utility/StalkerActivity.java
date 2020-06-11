package tech.gruppone.stalker.app.utility;

import androidx.appcompat.app.AppCompatActivity;
import tech.gruppone.stalker.app.utility.location.GooglePositionInterface;

public class StalkerActivity extends AppCompatActivity {

  void checkPermissions() {
    new GooglePositionInterface().checkPermissions(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    checkPermissions();
  }
}
