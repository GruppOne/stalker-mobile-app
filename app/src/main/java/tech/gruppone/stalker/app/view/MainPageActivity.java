package tech.gruppone.stalker.app.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.StalkerActivity;
import tech.gruppone.stalker.app.utility.location.GeofenceHandler;
import tech.gruppone.stalker.app.viewmodel.MainPageViewModel;

public class MainPageActivity extends StalkerActivity {

  FragmentManager fragmentManager = getSupportFragmentManager();
  DialogInterface.OnClickListener dialogClickListener =
      (dialog, which) -> {
        switch (which) {
          case DialogInterface.BUTTON_POSITIVE:
            MainPageViewModel.logout();
            MainPageActivity.super.onBackPressed();
            break;

          case DialogInterface.BUTTON_NEGATIVE:
            // Do nothing
            break;
        }
      };

  @SuppressWarnings("unused")
  GeofenceHandler geofenceHandler = null;

  MainPageViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    geofenceHandler = new GeofenceHandler(this);

    MaterialToolbar topAppBar = findViewById(R.id.topAppBarPages);

    setSupportActionBar(topAppBar);

    topAppBar.setOnMenuItemClickListener(
        item -> {
          if (item.getItemId() == R.id.anonymousMenuItem) {
            viewModel.toggleAnonymous();

            boolean anonymous = CurrentSessionSingleton.getInstance().isAnonymous();

            Toast.makeText(
                    this,
                    anonymous ? getString(R.string.wentAnonymous) : getString(R.string.wentKnown),
                    Toast.LENGTH_SHORT)
                .show();

            item.setIcon(
                anonymous
                    ? R.drawable.ic_visibility_black_24dp
                    : R.drawable.ic_visibility_off_black_24dp);

            item.setTitle(anonymous ? R.string.goKnown : R.string.goAnonymous);

            return true;
          }
          return false;
        });

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

    bottomNavigationView.setOnNavigationItemSelectedListener(
        menuItem -> {
          viewModel.setSelectedMenuItemId(menuItem.getItemId());
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

    // bottomNavigationView.setOnNavigationItemReselectedListener(menuItem -> {});

    bottomNavigationView.setSelectedItemId(viewModel.getSelectedMenuItemId());
  }

  @Override
  public void onBackPressed() {
    new MaterialAlertDialogBuilder(this)
        .setMessage("Do you want to logout?")
        .setPositiveButton("Yes", dialogClickListener)
        .setNegativeButton("No", dialogClickListener)
        .show();
  }

  private void setOrganizationsPage() {
    fragmentManager
        .beginTransaction()
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.report_fragment)))
        .show(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .commit();
  }

  private void setConnectedPage() {
    fragmentManager
        .beginTransaction()
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.report_fragment)))
        .show(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .commit();
  }

  private void setReportPage() {
    ;
    fragmentManager
        .beginTransaction()
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.connected_fragment)))
        .hide(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.organizations_fragment)))
        .show(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.report_fragment)))
        .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(@NonNull Menu menu) {
    getMenuInflater().inflate(R.menu.app_bar_menu, menu);
    return true;
  }

  @Override
  protected void onDestroy() {
    if (isFinishing()) {
      Log.i("finishing", "closing the activity");
      geofenceHandler.clearGeofenses();
    }
    super.onDestroy();
  }
}
