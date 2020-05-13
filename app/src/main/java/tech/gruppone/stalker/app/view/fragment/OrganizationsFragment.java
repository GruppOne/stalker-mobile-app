package tech.gruppone.stalker.app.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.OrganizationListAdapter;
import tech.gruppone.stalker.app.view.MainPageActivity;
import tech.gruppone.stalker.app.viewmodel.fragment.OrganizationsViewModel;

public class OrganizationsFragment extends Fragment {

  OrganizationsViewModel viewModel=new OrganizationsViewModel();

  public OrganizationsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    viewModel = new ViewModelProvider(this).get(OrganizationsViewModel.class);
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view =inflater.inflate(R.layout.fragment_organizations, container, false);

    view.findViewById(R.id.reloadButton)
      .setOnClickListener(v -> OrganizationsFragment.this.loadOrganizations());

    RecyclerView recyclerView = view.findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getOrgsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

    recyclerView.setAdapter(adapter);

    return view;
  }

  public void loadOrganizations() {
    viewModel.loadOrganizations();
  }
}
