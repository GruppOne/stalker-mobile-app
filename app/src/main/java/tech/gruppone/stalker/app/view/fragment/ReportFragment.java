package tech.gruppone.stalker.app.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.ReportListAdapter;
import tech.gruppone.stalker.app.utility.ReportListAdapter2;
import tech.gruppone.stalker.app.viewmodel.fragment.ReportViewModel;

public class ReportFragment extends Fragment {

  private View view;
  private ReportViewModel reportModel;

  public ReportFragment() {
  }


  @Override
  @NonNull
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    reportModel = new ViewModelProvider(this).get(ReportViewModel.class);
    RecyclerView recyclerView = view.findViewById(R.id.reportRecyclerView);



    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    reportModel.getUsersHistory(CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());



    /*List<Integer> xd= new ArrayList<>();
    xd.add(1);
    xd.add(2);
    xd.add(3);

    ReportListAdapter reportListAdapter = new ReportListAdapter(xd);*/
    ReportListAdapter2 reportListAdapter = new ReportListAdapter2();
    reportModel.getUsersLiveData().observe(getViewLifecycleOwner(), reportListAdapter::submitList);
    /*reportModel.getUsersLiveData().observe(getViewLifecycleOwner(), result -> reportListAdapter.notifyDataSetChanged());*/



    recyclerView.setAdapter(reportListAdapter);


    /*reportModel.getUsersHistory(CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());
    CurrentSessionSingleton.getInstance().getOrganizationsName().observe(getViewLifecycleOwner(),
      reportListAdapter.submitList());*/

    //recyclerView.setAdapter(reportListAdapter);


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
    reportModel.getUsersHistory(CurrentSessionSingleton.getInstance().getLoggedUser().getValue().getId());
  }
}
