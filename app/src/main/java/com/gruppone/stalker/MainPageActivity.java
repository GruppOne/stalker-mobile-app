package com.gruppone.stalker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainPageActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private MainPageViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mainpage);

    viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);

    recyclerView = (RecyclerView) findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new MyAdapter(viewModel.organizationDataSet);
    recyclerView.setAdapter(mAdapter);
  }
}
