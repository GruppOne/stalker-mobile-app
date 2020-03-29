package com.gruppone.stalker;

<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData;
=======
import android.util.Pair;

import java.util.ArrayList;
>>>>>>> feat: add methods to check if a point is inside an organization
import java.util.List;
import lombok.Getter;

public class CurrentSessionSingleton {

  private static CurrentSessionSingleton instance;

  @Getter
  private User loggedUser;

  private MutableLiveData<List<Organization>> organizations = new MutableLiveData<>();

  private CurrentSessionSingleton() {

  }

  void setOrganizations(List<Organization> orgList) {
    organizations.postValue(orgList);
    //localization entry point
  }

  boolean zeroOrganizations() {
    return organizations.getValue() == null || organizations.getValue().isEmpty();
  }

  public List<Integer> getInsideId(Pair<Double,Double> point) {
    List<Integer> ret = new ArrayList<>();
    for (Organization org:organizations) {
      if (org.isInside(point))
        ret.add(org.getId());
    }
    return ret;
  }

  public static synchronized CurrentSessionSingleton getInstance() {
    if (instance == null) {
      instance = new CurrentSessionSingleton();
    }
    return instance;
  }
}
