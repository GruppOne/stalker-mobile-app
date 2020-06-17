package tech.gruppone.stalker.app.utility;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.ReportListAdapter.UserOrganizationViewHolder;

@SuppressLint("SetTextI18n")
public class ReportListAdapter
    extends ListAdapter<LiveData<UserOrganizationHistory>, UserOrganizationViewHolder> {

  public ReportListAdapter() {
    super(
        new ItemCallback<LiveData<UserOrganizationHistory>>() {
          @Override
          public boolean areItemsTheSame(
              @NonNull LiveData<UserOrganizationHistory> oldItem,
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
  public UserOrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.itemreport_fragment, parent, false);
    return new UserOrganizationViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull UserOrganizationViewHolder holder, int position) {
    holder.bindTo(getItem(position));
  }

  public static class UserOrganizationViewHolder extends RecyclerView.ViewHolder {

    private TextView TtimestampTime;
    private TextView TtimestampDate;
    private TextView TplaceName;
    private TextView Taddress;
    private TextView Tcity;
    private TextView Tinside;
    private TextView TorganizationName;

    public UserOrganizationViewHolder(@NonNull View v) {
      super(v);
      TtimestampDate = v.findViewById(R.id.tvTimestampDate);
      TtimestampTime = v.findViewById(R.id.tvTimestampTime);
      TplaceName = v.findViewById(R.id.tvPlaceName);
      Tinside = v.findViewById(R.id.tvInside);
      TorganizationName = v.findViewById(R.id.tvOrganizationName);
      Taddress = v.findViewById(R.id.tvPlaceAddress);
      Tcity = v.findViewById(R.id.tvPlaceCity);
    }

    public void bindTo(@NonNull LiveData<UserOrganizationHistory> userOrganizationHistory) {
      UserOrganizationHistory userOrg = Objects.requireNonNull(userOrganizationHistory.getValue());

      TtimestampDate.setText(getDate(userOrg.getTimestamp()));
      TtimestampTime.setText("at " + getHours(userOrg.getTimestamp()));
      TplaceName.setText(userOrg.getPlace().getName());
      Tinside.setText(userOrg.getInside() ? "you entered on" : "you went out on");
      TorganizationName.setText(userOrg.getOrganization().getName());
      Taddress.setText(userOrg.getPlace().getAddress() + ", ");
      Tcity.setText(userOrg.getPlace().getCity());
    }

    String getDate(long milliseconds) {
      DateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyy", Locale.forLanguageTag("ENG"));
      return dateFormat.format(new Date(milliseconds));
    }

    String getHours(long milliseconds) {
      DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
      return dateFormat.format(new Date(milliseconds));
    }
  }
}
