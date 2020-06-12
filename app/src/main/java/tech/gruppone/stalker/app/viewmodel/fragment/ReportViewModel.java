package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.model.OrganizationModel;
import tech.gruppone.stalker.app.model.fragment.OrganizationsModel;
import tech.gruppone.stalker.app.model.fragment.ReportModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReportViewModel extends ViewModel {

  private ReportModel model = new ReportModel();

  private OrganizationModel organizationsModel = new OrganizationModel();

  public void getUsersHistory(int id) {
    model.getUsersHistory(id);
  }

  @NonNull
  public LiveData<List<LiveData<UserOrganizationHistory>>> getUsersLiveData() {

    return Transformations.map(
        model.getOrgsHistoryLiveData(),
        input -> {
          List<LiveData<UserOrganizationHistory>> liveData = new ArrayList<>(input.values());
          for(LiveData<UserOrganizationHistory>  userOrganizationHistoryLiveData : liveData){
              organizationsModel.loadPlaces(userOrganizationHistoryLiveData.getValue().getOrganizationId());
          }
          return liveData;
        });
  }
}
