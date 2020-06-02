package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.gruppone.stalker.app.business.OrganizationHistory;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.model.fragment.ReportModel;
import tech.gruppone.stalker.app.utility.ReportListAdapter.UserHistoryViewHolder;


@Data
@EqualsAndHashCode(callSuper=false)
public class ReportViewModel extends ViewModel {


  private ReportModel model = new ReportModel();

  public void getUsersHistory(int id){
    model.getUsersHistory(id);

  }


  @NonNull
  public LiveData<List<UserOrganizationHistory>> getUsersLiveData() {

    /*List <OrganizationHistory> values = new ArrayList<>(model.getOrgsLiveData().getValue().values());
    List<UserOrganizationHistory> toReturn = new ArrayList<>();
    for(OrganizationHistory org : values){
      List<UserOrganizationHistory> history = org.getHistory();
      for(UserOrganizationHistory userOrg : history){
        toReturn.add(userOrg);
      }
    }
    return new MutableLiveData<>(toReturn);
    //Transformations.switchMap(model.getOrgsLiveData().getValue(), result -> new ArrayList<UserOrganizationHistory>(result));*/
    return model.getOrgsLiveData2();
  }

}
