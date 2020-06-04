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
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.ReportListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.ReportViewModel;

public class ReportFragment extends Fragment {

  private View view;
  private ReportViewModel reportViewModel;

  public ReportFragment() {
  }


  @Override
  @NonNull
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
    RecyclerView recyclerView = view.findViewById(R.id.reportRecyclerView);


    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    reportViewModel.getUsersHistory(
      Objects.requireNonNull(CurrentSessionSingleton.getInstance().getLoggedUser().getValue()).getId());

    ReportListAdapter reportListAdapter = new ReportListAdapter();
    reportViewModel.getUsersLiveData().observe(getViewLifecycleOwner(),
      reportListAdapter::submitList);


    recyclerView.setAdapter(reportListAdapter);



  }

  @Override
  @NonNull
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.report_fragment, container, false);

    view.findViewById(R.id.reportReloadButton)
      .setOnClickListener(v -> ReportFragment.this.getUsersHistory());
    return view;
  }

  public void getUsersHistory() {
    reportViewModel
      .getUsersHistory(CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());
  }
}
