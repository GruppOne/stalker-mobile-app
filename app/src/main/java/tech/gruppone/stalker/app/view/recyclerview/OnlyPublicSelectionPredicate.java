package tech.gruppone.stalker.app.view.recyclerview;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import tech.gruppone.stalker.app.utility.CurrentSessionSingleton;
import tech.gruppone.stalker.app.utility.excpetions.OrganizationNotFoundException;

public class OnlyPublicSelectionPredicate extends SelectionTracker.SelectionPredicate<Long> {

  @Override
  public boolean canSetStateForKey(@NonNull Long organizationId, boolean nextState) {
    try {
      return !(requireNonNull(
              CurrentSessionSingleton.getInstance()
                  .getOrganization(organizationId.intValue())
                  .getValue())
          .isPrivate());
    } catch (OrganizationNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean canSetStateAtPosition(int position, boolean nextState) {
    return true;
  }

  @Override
  public boolean canSelectMultiple() {
    return true;
  }
}
