package tech.gruppone.stalker.app.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.utility.location.GooglePositionInterface;

public class MainPageActivity extends StalkerActivity {

  FragmentManager fragmentManager = getSupportFragmentManager();

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

    ((BottomNavigationView) findViewById(R.id.bottom_navigation))
        .setOnNavigationItemReselectedListener(menuItem -> {});

    setOrganizationsPage();

    GooglePositionInterface.startLocationUpdates(this);
  }

  private void setOrganizationsPage() {
    fragmentManager
        .beginTransaction()
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .show(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .commit();
  }

  private void setConnectedPage() {
    fragmentManager
        .beginTransaction()
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .show(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .commit();
  }

  private void setReportPage() {}
}
