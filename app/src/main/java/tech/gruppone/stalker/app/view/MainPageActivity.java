package tech.gruppone.stalker.app.view;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.utility.location.GooglePositionInterface;
import tech.gruppone.stalker.app.viewmodel.MainPageViewModel;

public class MainPageActivity extends StalkerActivity {

  FragmentManager fragmentManager = getSupportFragmentManager();

  MainPageViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    MaterialToolbar topAppBar = findViewById(R.id.topAppBarPages);

    setSupportActionBar(topAppBar);

    requireNonNull(getSupportActionBar()).invalidateOptionsMenu();

    topAppBar.getMenu().add("bubu");

    requireNonNull(getSupportActionBar()).invalidateOptionsMenu();

    topAppBar.setOnMenuItemClickListener(
        item -> {
          if (item.getItemId() == R.id.anonymousMenuItem) {
            viewModel.toggleAnonymous();

            boolean anonymous = CurrentSessionSingleton.getInstance().isAnonymous();

            item.setIcon(
                anonymous
                    ? R.drawable.ic_visibility_black_24dp
                    : R.drawable.ic_visibility_off_black_24dp);

            item.setTitle(anonymous ? R.string.goKnown : R.string.goAnonymous);

            item.setContentDescription(
                getString(
                    anonymous ? R.string.goKnownDescription : R.string.goAnonymousDescription));
            return true;
          }
          return false;
        });

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
        .hide(requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .show(requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .commit();
  }

  private void setConnectedPage() {
    fragmentManager
        .beginTransaction()
        .hide(requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .show(requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .commit();
  }

  private void setReportPage() {}

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.app_bar_menu, menu);
    return true;
  }
}
