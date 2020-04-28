package com.gruppone.stalker;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

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
