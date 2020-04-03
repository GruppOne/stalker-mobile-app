package com.gruppone.stalker;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gruppone.stalker.OrganizationListAdapter.OrgViewHolder;
import java.util.List;

public class MainPageActivity extends StalkerActivity {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter<OrgViewHolder> mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private MainPageViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    loadOrganizations();

    final Observer<List<Organization>> organizationDataSetObserver = newOrganizationDataSet -> mAdapter
      .notifyDataSetChanged();

    viewModel.getOrgsLiveData()
      .observe(this, organizationDataSetObserver);

    recyclerView = findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new OrganizationListAdapter(viewModel.getOrgsLiveData()
      .getValue());

    mAdapter.notifyDataSetChanged();
    recyclerView.setAdapter(mAdapter);
  }

  public void loadOrganizations() {
    viewModel.loadOrganizations();
  }
}
