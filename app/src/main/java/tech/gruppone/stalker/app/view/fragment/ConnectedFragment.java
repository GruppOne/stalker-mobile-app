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
import tech.gruppone.stalker.app.viewmodel.fragment.ConnectedViewModel;

public class ConnectedFragment extends Fragment {

  private ConnectedViewModel viewModel;
  private View view;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.connected_fragment, container, false);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    viewModel = new ViewModelProvider(this).get(ConnectedViewModel.class);

    RecyclerView recyclerView = view.findViewById(R.id.connectedRecyclerView);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getConnectedOrganizations().observe(getViewLifecycleOwner(), adapter::submitList);

    recyclerView.setAdapter(adapter);
  }
}
