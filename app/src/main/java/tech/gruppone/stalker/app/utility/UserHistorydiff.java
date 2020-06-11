package tech.gruppone.stalker.app.utility;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import java.util.Objects;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;

public class UserHistorydiff extends DiffUtil.Callback {

  private final List<LiveData<UserOrganizationHistory>> oldUserOrganizationHistoryList;
  private final List<LiveData<UserOrganizationHistory>> newUserOrganizationHistortList;

  public UserHistorydiff(
    List<LiveData<UserOrganizationHistory>> oldUserOrganizationHistoryList,
    List<LiveData<UserOrganizationHistory>> newUserOrganizationHistortList) {
    this.oldUserOrganizationHistoryList = oldUserOrganizationHistoryList;
    this.newUserOrganizationHistortList = newUserOrganizationHistortList;
  }



  @Override
  public int getOldListSize() {
    return oldUserOrganizationHistoryList.size();
  }

  @Override
  public int getNewListSize() {
    return newUserOrganizationHistortList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldUserOrganizationHistoryList.get(oldItemPosition).getValue().getTimestamp() == newUserOrganizationHistortList.get(newItemPosition).getValue().getTimestamp();
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return Objects.equals(oldUserOrganizationHistoryList.get(oldItemPosition).getValue(),
      newUserOrganizationHistortList.get(newItemPosition).getValue());
  }
}
