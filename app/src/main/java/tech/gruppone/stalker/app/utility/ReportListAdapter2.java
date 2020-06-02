package tech.gruppone.stalker.app.utility;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.ReportListAdapter2.UserOrganizationViewHolder;

public class ReportListAdapter2 extends ListAdapter<UserOrganizationHistory, UserOrganizationViewHolder> {

  public ReportListAdapter2() {
    super(    new ItemCallback<UserOrganizationHistory>() {
      @Override
      public boolean areItemsTheSame(
        @NonNull UserOrganizationHistory oldItem,
        @NonNull UserOrganizationHistory newItem) {
        return oldItem.equals(newItem);
      }

      @Override
      public boolean areContentsTheSame(
        @NonNull UserOrganizationHistory oldItem,
        @NonNull UserOrganizationHistory newItem) {
        return oldItem.getPlaceId()== oldItem.getPlaceId();
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

    public void bindTo(@NonNull UserOrganizationHistory userOrganizationHistory) {
      Ttimestamp.setText(userOrganizationHistory.getTimestamp());
      TplaceName.setText(String.valueOf(userOrganizationHistory.getPlaceId()));
      Tinside.setText(userOrganizationHistory.getInside().toString());
    }
  }

}
