package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.lifecycle.ViewModel;
import tech.gruppone.stalker.app.model.fragment.ReportModel;

public class ReportViewModel extends ViewModel {

  private ReportModel model = new ReportModel();

  public void getUsersHistory(int id){
    model.getUsersHistory(id);
  }
}
