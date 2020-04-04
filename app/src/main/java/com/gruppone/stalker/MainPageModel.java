package com.gruppone.stalker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MainPageModel {
  private MutableLiveData<ArrayList<String>> organizationDataSet = new MutableLiveData<>();

  LiveData<ArrayList<String>> getOrganizationDataSet() {
    organizationDataSet.setValue(new ArrayList<String>() {{
      add("Università degli studi di Padova");
      add("Alphabet Inc.");
      add("Imola Informatica");
      add("Touch Multimedia");
    }});
    return organizationDataSet;
  }

  void Reload() {
    //Chiamata a WebSingleton
    organizationDataSet.setValue(organizationDataSet.getValue());
  }
}
