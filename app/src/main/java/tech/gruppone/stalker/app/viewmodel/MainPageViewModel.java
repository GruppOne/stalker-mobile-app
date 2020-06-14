package tech.gruppone.stalker.app.viewmodel;

import androidx.annotation.IdRes;
import androidx.lifecycle.ViewModel;
import lombok.Getter;
import lombok.Setter;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.model.MainPageModel;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;

public class MainPageViewModel extends ViewModel {
  private final MainPageModel model = new MainPageModel();
  @Getter @Setter @IdRes private int selectedMenuItemId = R.id.organizations_page;

  public static void logout() {
    CurrentSessionSingleton.logout();
  }

  public void toggleAnonymous() {
    model.toggleAnonymous();
  }
}
