package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.model.OrganizationModel;
import tech.gruppone.stalker.app.model.fragment.ReportModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReportViewModel extends ViewModel {

  private ReportModel model = new ReportModel();

  private OrganizationModel organizationModel = new OrganizationModel();

  public void getUsersHistory(int id) {
    model.getUsersHistory(id);
  }

  @NonNull
  public LiveData<List<LiveData<UserOrganizationHistory>>> getUsersLiveData() {

    return Transformations.map(
        model.getOrgsHistoryLiveData(), input -> new ArrayList<>(input.values()));
  }
}
