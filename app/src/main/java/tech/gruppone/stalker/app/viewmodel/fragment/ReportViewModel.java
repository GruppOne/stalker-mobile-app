package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
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


}
