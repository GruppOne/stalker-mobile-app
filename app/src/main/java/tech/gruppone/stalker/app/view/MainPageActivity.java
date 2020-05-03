package tech.gruppone.stalker.app.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.R;
import tech.gruppone.stalker.app.business.location.GooglePositionInterface;
import tech.gruppone.stalker.app.viewmodel.MainPageViewModel;
import tech.gruppone.stalker.app.utility.OrganizationListAdapter;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.StalkerActivity;

public class MainPageActivity extends StalkerActivity {

  private MainPageViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    findViewById(R.id.reloadButton)
        .setOnClickListener(v -> MainPageActivity.this.loadOrganizations());

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    RecyclerView recyclerView = findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getOrgsLiveData().observe(this, adapter::submitList);

    recyclerView.setAdapter(adapter);

    // XXX THIS WILL BE REFACTORED AWAY AFTER THE POC
    // XXX THE IDEA IS THAT WE START TRACING LOCATIONS AFTER THE LOGIN
    // XXX MOMENTARILY, WE LEAVE IT HERE
    // XXX #LucaNonGuardareStaRoba
    if (firstRun) {
      GooglePositionInterface.startLocationUpdates(this);
      firstRun = false;
    }
  }

  static boolean firstRun = true;

  public void loadOrganizations() {
    viewModel.loadOrganizations();
  }
}
