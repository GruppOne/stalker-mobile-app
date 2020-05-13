package tech.gruppone.stalker.app.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.utility.location.GooglePositionInterface;

public class MainPageActivity extends StalkerActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    ((BottomNavigationView) findViewById(R.id.bottom_navigation))
        .setOnNavigationItemSelectedListener(
            menuItem -> {
              switch (menuItem.getItemId()) {
                case R.id.organizations_page:
                  MainPageActivity.this.setOrganizationsPage();
                  return true;
                case R.id.connected_page:
                  MainPageActivity.this.setConnectedPage();
                  return true;
                case R.id.report_page:
                  MainPageActivity.this.setReportPage();
                  return true;
                default:
                  return false;
              }
            });

    GooglePositionInterface.startLocationUpdates(this);
  }

  private void setOrganizationsPage() {}

  private void setConnectedPage() {}

  private void setReportPage() {}
}
