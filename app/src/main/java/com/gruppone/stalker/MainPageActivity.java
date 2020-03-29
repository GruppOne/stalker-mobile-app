package com.gruppone.stalker;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainPageActivity extends StalkerActivity {
  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private MainPageViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    final Observer<ArrayList<String>> organizationDataSetObserver = newOrganizationDataSet -> mAdapter.notifyDataSetChanged();

    viewModel.getOrganizationDataSet()
             .observe(this, organizationDataSetObserver);

    recyclerView = findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new OrganizationListAdapter(viewModel.getOrganizationDataSet()
                                                    .getValue());

    mAdapter.notifyDataSetChanged();
    recyclerView.setAdapter(mAdapter);
  }
}
