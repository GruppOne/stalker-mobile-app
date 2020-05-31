package tech.gruppone.stalker.app.utility;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.UserHistory;
import tech.gruppone.stalker.app.utility.ReportListAdapter.HistoryViewHolder;

public class ReportListAdapter extends ListAdapter<UserHistory, HistoryViewHolder> {


  public void setUserHistories(
    List<UserHistory> userHistories) {
    this.userHistories = userHistories;
    notifyDataSetChanged();
  }

  List<UserHistory> userHistories;

  public ReportListAdapter() {
    super(
      new ItemCallback<UserHistory>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserHistory oldItem,
          @NonNull UserHistory newItem) {
          return oldItem.getOrganizationId() == newItem.getOrganizationId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserHistory oldItem,
          @NonNull UserHistory newItem) {
          return oldItem.equals(newItem);
        }
      });
  }

  @NonNull
  @Override
  public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // replace viewType with the correct xml element
    return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent, false));
  }

  @Override
  public void onBindViewHolder(
    @NonNull HistoryViewHolder holder, int position) {
    UserHistory userHistory = userHistories.get(position);
    holder.tvOrganizationName.setText(userHistory.getOrganizationId());
    holder.tvPlaceName.setText(userHistory.getOrganizationHistoryList()
      .get(position).getUserId());
    holder.tvTimestamp.setText(userHistory.getOrganizationHistoryList()
      .get(position).getHistory().get(position).getTimestamp().toString());

  }

  public static class HistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView tvOrganizationName;
    private TextView tvPlaceName;
    private TextView tvTimestamp;

    public HistoryViewHolder(@NonNull View itemView) {
      super(itemView);
      tvOrganizationName = itemView.findViewById( R.id.tvOrganizationName);
      tvPlaceName = itemView.findViewById(R.id.tvPlaceName);
      tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
    }


  }

}
