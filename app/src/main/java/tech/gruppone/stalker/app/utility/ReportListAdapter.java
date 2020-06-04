package tech.gruppone.stalker.app.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Date;
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.ReportListAdapter.UserOrganizationViewHolder;

public class ReportListAdapter extends ListAdapter<LiveData<UserOrganizationHistory>, UserOrganizationViewHolder> {

  public ReportListAdapter() {
    super(    new ItemCallback <LiveData<UserOrganizationHistory>> (){
      @Override
      public boolean areItemsTheSame(
        @NonNull LiveData <UserOrganizationHistory> oldItem,
        @NonNull LiveData<UserOrganizationHistory> newItem) {
        return oldItem.equals(newItem);
      }

      @Override
      public boolean areContentsTheSame(
        @NonNull LiveData<UserOrganizationHistory> oldItem,
        @NonNull LiveData<UserOrganizationHistory> newItem) {
        UserOrganizationHistory oldItemValue = oldItem.getValue();
        UserOrganizationHistory newItemValue = newItem.getValue();
        return oldItemValue.getPlaceId() == newItemValue.getPlaceId();
      }
    });
  }


  @NonNull
  @Override
  public UserOrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
    int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemreport_fragment, parent, false);
    return new UserOrganizationViewHolder(v);
  }

  @Override
  public void onBindViewHolder(
    @NonNull UserOrganizationViewHolder holder, int position) {
    holder.bindTo(getItem(position));
  }

  public static class UserOrganizationViewHolder extends RecyclerView.ViewHolder {

    private TextView Ttimestamp;
    private TextView TplaceName;
    private TextView Tinside;

    public UserOrganizationViewHolder(@NonNull View v) {
      super(v);
      Ttimestamp = v.findViewById(R.id.tvTimestamp);
      TplaceName = v.findViewById(R.id.tvPlaceName);
      Tinside = v.findViewById(R.id.tvInside);
    }


    public void bindTo(@NonNull LiveData<UserOrganizationHistory> userOrganizationHistory) {
      UserOrganizationHistory userOrg = Objects.requireNonNull(userOrganizationHistory.getValue());
      Ttimestamp.setText(String.valueOf(new Date(userOrg.getTimestamp())));
      TplaceName.setText(String.valueOf(userOrg.getPlaceId()));
      Tinside.setText(userOrg.getInside() ? "you entered on ": "you went out on");
      TorganizationName.setText(userOrg.getOrganizationName());
    }
  }

}
