package tech.gruppone.stalker.app.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.OrganizationListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.OrganizationsViewModel;

public class OrganizationsFragment extends Fragment {

  private OrganizationsViewModel viewModel;
  private View view;

  public OrganizationsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    viewModel = new ViewModelProvider(this).get(OrganizationsViewModel.class);

    RecyclerView recyclerView = view.findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getOrgsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

    recyclerView.setAdapter(adapter);
  }

  @Override
  @NonNull
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.organizations_fragment, container, false);

    view.findViewById(R.id.reloadButton)
        .setOnClickListener(v -> OrganizationsFragment.this.loadOrganizations());

    return view;
  }

  public void loadOrganizations() {
    viewModel.loadOrganizations();
  }
}
