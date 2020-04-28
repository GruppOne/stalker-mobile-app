package com.gruppone.stalker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainPageActivity extends StalkerActivity {

  private MainPageViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    findViewById(R.id.reloadButton).setOnClickListener(
      v -> MainPageActivity.this.loadOrganizations());

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    RecyclerView recyclerView = findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getOrgsLiveData()
      .observe(this, adapter::submitList);

    recyclerView.setAdapter(adapter);

    //XXX THIS WILL BE REFACTORED AWAY AFTER THE POC
    //XXX THE IDEA IS THAT WE START TRACING LOCATIONS AFTER THE LOGIN
    //XXX MOMENTARILY, WE LEAVE IT HERE
    //XXX #LucaNonGuardareStaRoba
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
