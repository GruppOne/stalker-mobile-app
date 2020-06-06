package tech.gruppone.stalker.app.viewmodel;

import androidx.lifecycle.ViewModel;
import tech.gruppone.stalker.app.model.MainPageModel;

public class MainPageViewModel extends ViewModel {
  private MainPageModel model = new MainPageModel();

  public void toggleAnonymous() {
    model.toggleAnonymous();
  }
}
