package tech.gruppone.stalker.app.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONException;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.ReportListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.ReportViewModel;

public class ReportFragment extends Fragment {

  private View view;
  private ReportViewModel reportViewModel;
  @Getter private ReportListAdapter reportListAdapter;
  @Getter private List<LiveData<UserOrganizationHistory>> currentList = new ArrayList<>();
  @Getter private SearchView searchView;
  @Getter private RecyclerView recyclerView;

  public ReportFragment() {}

  @SneakyThrows
  @Override
  @NonNull
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

    searchView = view.findViewById(R.id.reportSearch_bar);
    recyclerView = view.findViewById(R.id.reportRecyclerView);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    reportListAdapter = new ReportListAdapter();

    recyclerView.setAdapter(reportListAdapter);
    reportViewModel
        .getUsersLiveData()
        .observe(
            getViewLifecycleOwner(),
            result -> {
              reportListAdapter.submitList(result);
              currentList = result;
            });

    SearchView.OnQueryTextListener queryTextListener = setSearchBarBehaviour();
    searchView.setOnQueryTextListener(queryTextListener);

    recyclerView.setAdapter(reportListAdapter);
  }

  @Override
  @NonNull
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    view = inflater.inflate(R.layout.report_fragment, container, false);
    view.findViewById(R.id.reportReloadButton)
        .setOnClickListener(
            v -> {
              try {
                ReportFragment.this.getUsersHistory();
              } catch (JSONException e) {
                e.printStackTrace();
              }
              searchView.setQuery("", true);
            });
    return view;
  }

  public void getUsersHistory() throws JSONException {
    reportViewModel.getUsersHistory(
        CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());
  }

  OnQueryTextListener setSearchBarBehaviour() {
    return new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (getSearchView().getQuery().length() == 0) {
          getReportListAdapter().submitList(getCurrentList());
          getRecyclerView().scrollToPosition(0);
        } else {
          List<LiveData<UserOrganizationHistory>> filteredData = new ArrayList<>();
          List<LiveData<UserOrganizationHistory>> userOrganizationHistory = getCurrentList();
          if (newText.isEmpty()) {
            filteredData = userOrganizationHistory;
          } else {
            List<LiveData<UserOrganizationHistory>> filteredElements = new ArrayList<>();
            for (LiveData<UserOrganizationHistory> useOrg : userOrganizationHistory) {
              if (Objects.requireNonNull(useOrg.getValue())
                      .getOrganization()
                      .getName()
                      .toLowerCase()
                      .contains(newText.toLowerCase().trim())
                  || Objects.requireNonNull(useOrg.getValue().getPlace().getName().toLowerCase())
                      .contains(newText.toLowerCase().trim())
                  || Objects.requireNonNull(useOrg.getValue().getPlace().getAddress().toLowerCase())
                      .contains(newText.toLowerCase().trim())
                  || Objects.requireNonNull(useOrg.getValue().getPlace().getCity().toLowerCase())
                      .contains(newText.toLowerCase().trim())) {
                filteredElements.add(useOrg);
                filteredData = filteredElements;
              }
            }
          }
          getReportListAdapter().submitList(filteredData);
        }
        return true;
      }
    };
  }
}
