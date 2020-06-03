package tech.gruppone.stalker.app.view.fragment;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.SelectionTracker.SelectionObserver;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.view.recyclerview.OrgListItemDetailsLookup;
import tech.gruppone.stalker.app.view.recyclerview.OrganizationItemKeyProvider;
import tech.gruppone.stalker.app.view.recyclerview.OrganizationListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.ConnectedViewModel;

public class ConnectedFragment extends Fragment {

  @SuppressWarnings("FieldCanBeLocal")
  private ConnectedViewModel viewModel;

  private View view;

  @Override
  @NonNull
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

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

    recyclerView.setLayoutManager(layoutManager);

    DividerItemDecoration dividerItemDecoration =
        new DividerItemDecoration(requireNonNull(getActivity()), layoutManager.getOrientation());

    recyclerView.addItemDecoration(dividerItemDecoration);

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getConnectedOrganizations().observe(getViewLifecycleOwner(), adapter::submitList);

    recyclerView.setAdapter(adapter);

    SelectionTracker<Long> selectionTracker =
        new SelectionTracker.Builder<>(
                "organizationListSelection",
                recyclerView,
                new OrganizationItemKeyProvider(recyclerView),
                new OrgListItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build();

    selectionTracker.addObserver(
        new SelectionObserver<Long>() {
          @Override
          public void onSelectionChanged() {
            super.onSelectionChanged();
          }
        });

    adapter.setSelectionTracker(selectionTracker);
  }
}
