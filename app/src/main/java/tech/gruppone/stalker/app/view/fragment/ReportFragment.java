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
import tech.gruppone.stalker.app.model.fragment.ReportModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.ReportListAdapter;
import tech.gruppone.stalker.app.viewmodel.fragment.ReportViewModel;

public class ReportFragment extends Fragment {

  private View view;
  private ReportViewModel reportModel;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.report_fragment, container, false);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    reportModel = new ViewModelProvider(this).get(ReportViewModel.class);
    RecyclerView recyclerView = view.findViewById(R.id.organizationRecyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    ReportListAdapter reportListAdapter = new ReportListAdapter();

    reportModel.getUsersHistory(CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());

    recyclerView.setAdapter(reportListAdapter);


  }
}
