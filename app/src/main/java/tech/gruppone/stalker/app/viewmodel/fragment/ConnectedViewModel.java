package tech.gruppone.stalker.app.viewmodel.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.fragment.ConnectedModel;

public class ConnectedViewModel extends ViewModel {
  ConnectedModel model = new ConnectedModel();

  @NonNull
  public LiveData<List<Organization>> getConnectedOrganizations() {
    return model.getConnectedOrganizations();
  }
}
