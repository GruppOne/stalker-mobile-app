package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.gruppone.stalker.app.business.OrganizationHistory;
import tech.gruppone.stalker.app.model.fragment.ReportModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReportViewModel extends ViewModel {

  private List<Integer> ids = new ArrayList<>();

  private ReportModel model = new ReportModel();

  public void getUsersHistory(int id){
    model.getUsersHistory(id);

    /*Set<Integer> keys =CurrentSessionSingleton.getInstance().getOrganizationsName().getValue().keySet();
    System.out.println(keys);
    ids.addAll(keys); */
;
  }


  @NonNull
  public List<Integer> getOrgsLiveData() {
    Set<Integer> keys = model.getOrgsLiveData().getValue().keySet();
    ids.addAll(ids);
    return ids;
  }

}
