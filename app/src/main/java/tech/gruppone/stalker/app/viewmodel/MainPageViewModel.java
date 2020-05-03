package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.model.MainPageModel;

public class MainPageViewModel extends ViewModel {

  private MainPageModel model = new MainPageModel();

  public void loadOrganizations() {
    model.loadOrganizations();
  }

  @NonNull
  public LiveData<List<Organization>> getOrgsLiveData() {
    return model.getOrgsLiveData();
  }
}
