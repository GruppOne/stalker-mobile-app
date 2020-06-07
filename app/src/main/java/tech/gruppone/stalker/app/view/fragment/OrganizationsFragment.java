package tech.gruppone.stalker.app.view.fragment;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ActionMode.Callback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.SelectionTracker.SelectionObserver;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.view.recyclerview.OnlyPublicSelectionPredicate;
import tech.gruppone.stalker.app.view.recyclerview.OrgListItemDetailsLookup;
import tech.gruppone.stalker.app.view.recyclerview.OrganizationItemKeyProvider;
import tech.gruppone.stalker.app.view.recyclerview.OrganizationListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.OrganizationsViewModel;

public class OrganizationsFragment extends Fragment {

  private OrganizationsViewModel viewModel;
  private View view;

  // To get why this is protected instead of private, see ConnectedFragment::onActivityCreated()
  protected ActionMode actionMode = null;

  public OrganizationsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    viewModel = new ViewModelProvider(this).get(OrganizationsViewModel.class);

    // To get the reason for this local variable, see ConnectedFragment::onActivityCreated()
    OrganizationsViewModel viewModel = this.viewModel;

    RecyclerView recyclerView = view.findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

    recyclerView.setLayoutManager(layoutManager);

    DividerItemDecoration dividerItemDecoration =
        new DividerItemDecoration(requireNonNull(getActivity()), layoutManager.getOrientation());

    recyclerView.addItemDecoration(dividerItemDecoration);

    OrganizationListAdapter adapter = new OrganizationListAdapter();

    viewModel.getOrgsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

    recyclerView.setAdapter(adapter);

    SelectionTracker<Long> selectionTracker =
        new SelectionTracker.Builder<>(
                "organizationListSelection",
                recyclerView,
                new OrganizationItemKeyProvider(recyclerView),
                new OrgListItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
            .withSelectionPredicate(new OnlyPublicSelectionPredicate())
            .build();

    selectionTracker.addObserver(
        new SelectionObserver<Long>() {
          @Override
          public void onSelectionChanged() {
            super.onSelectionChanged();

            // Safe cast, because we only use AppCompatActivity
            AppCompatActivity activity =
                (AppCompatActivity) OrganizationsFragment.this.getActivity();

            if (activity != null) {
              if (selectionTracker.hasSelection() && actionMode == null) {
                actionMode =
                    activity.startSupportActionMode(
                        new Callback() {
                          @Override
                          public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            mode.getMenuInflater()
                                .inflate(R.menu.contextual_action_bar_connect, menu);
                            return true;
                          }

                          @Override
                          public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return true;
                          }

                          @Override
                          public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            if (item.getItemId() == R.id.connectMenuItem) {
                              viewModel.connect(selectionTracker.getSelection().iterator());
                              selectionTracker.clearSelection();
                              return true;
                            }
                            return false;
                          }

                          @Override
                          public void onDestroyActionMode(ActionMode mode) {
                            selectionTracker.clearSelection();
                          }
                        });
              } else if (!selectionTracker.hasSelection() && actionMode != null) {
                actionMode.finish();
                actionMode = null;
              }

              if (selectionTracker.hasSelection() && actionMode != null) {
                actionMode.setTitle(
                    selectionTracker.getSelection().size() + " " + getString(R.string.selected));
              }
            }
          }
        });

    adapter.setSelectionTracker(selectionTracker);
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
