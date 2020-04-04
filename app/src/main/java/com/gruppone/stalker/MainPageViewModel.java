package com.gruppone.stalker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainPageViewModel extends ViewModel {
  private MainPageModel model = new MainPageModel();

  public LiveData<ArrayList<String>> getOrganizationDataSet() {
    return model.getOrganizationDataSet();
  }
}
