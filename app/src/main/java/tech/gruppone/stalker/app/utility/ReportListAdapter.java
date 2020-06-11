package tech.gruppone.stalker.app.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserOrganizationHistory;
import tech.gruppone.stalker.app.utility.ReportListAdapter.UserOrganizationViewHolder;

public class ReportListAdapter
    extends ListAdapter<LiveData<UserOrganizationHistory>, UserOrganizationViewHolder>
    implements Filterable {

  @Getter @Setter
  List<LiveData<UserOrganizationHistory>> dataList = getCurrentList();

  @Getter List<LiveData<UserOrganizationHistory>> filteredData = new ArrayList<>();

  List<LiveData<UserOrganizationHistory>> updateList = new ArrayList<>();

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

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence constraint) {
        List<LiveData<UserOrganizationHistory>> userOrganizationHistory = dataList;
        for(LiveData<UserOrganizationHistory> userOrg : dataList){
          System.out.println(userOrg.getValue());
        }
        String charString = constraint.toString();
        if (charString.isEmpty()) {
          filteredData = userOrganizationHistory;
        } else {
          List<LiveData<UserOrganizationHistory>> filteredElements = new ArrayList<>();
          for (LiveData<UserOrganizationHistory> useOrg : userOrganizationHistory) {
            if (Objects.requireNonNull(useOrg.getValue())
                    .getOrganizationName()
                    .toLowerCase()
                    .contains(constraint.toString().toLowerCase().trim()))/*
                || Objects.requireNonNull(useOrg.getValue().getPlace().getName().toLowerCase())
                    .contains(constraint.toString().toLowerCase().trim())
                || Objects.requireNonNull(useOrg.getValue().getPlace().getAddress().toLowerCase())
                    .contains(constraint.toString().toLowerCase().trim())
                || Objects.requireNonNull(useOrg.getValue().getPlace().getCity().toLowerCase())
                    .contains(constraint.toString().toLowerCase().trim()))*/ {
              filteredElements.add(useOrg);
            }
          }
          filteredData = filteredElements;
        }
        FilterResults filterResults = new FilterResults();;
        filterResults.values = filteredData;
        return filterResults;
      }

      @Override
      @SuppressWarnings("unchecked")
      protected void publishResults(CharSequence constraint, FilterResults results) {
        filteredData = (List<LiveData<UserOrganizationHistory>>) results.values;
        notifyDataSetChanged();
        updateList(filteredData);
      }
    };
  }

  void updateList(List<LiveData<UserOrganizationHistory>> list){
    final UserHistorydiff diff = new UserHistorydiff(updateList, list);
    System.out.println(list);
    final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diff);
    updateList.clear();
    updateList.addAll(list);
    diffResult.dispatchUpdatesTo(this);

  }

  public static class UserOrganizationViewHolder extends RecyclerView.ViewHolder {

    private TextView TtimestampTime;
    private TextView TtimestampDate;
    private TextView TplaceName;
    private TextView Taddress;
    private TextView Tcity;
    private TextView Tinside;

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
      Tinside.setText(userOrg.getInside() ? "you entered on " : "you went out on");
      TorganizationName.setText(userOrg.getOrganizationName());
      Taddress.setText(userOrg.getPlace().getAddress() + ", ");
      Tcity.setText(userOrg.getPlace().getCity());
    }

    String getDate(long milliseconds) {
      DateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyy", Locale.forLanguageTag("ENG"));
      return dateFormat.format(new Date(milliseconds));
    }

    String getHours(long milliseconds) {
      DateFormat dateFormat = new SimpleDateFormat("HH:mm");
      return dateFormat.format(new Date(milliseconds));
    }
  }
}
