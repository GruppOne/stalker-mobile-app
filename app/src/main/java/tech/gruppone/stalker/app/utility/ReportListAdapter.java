package tech.gruppone.stalker.app.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import tech.gruppone.stalker.app.R;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.UserHistoryViewHolder> {

  public List<Integer> ids;

  public ReportListAdapter(List<Integer> ids) {
    this.ids = ids;
  }

  @NonNull
  @Override
  public UserHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
    int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemreport_fragment, parent, false);
    return new UserHistoryViewHolder(v);
  }

  @Override
  public int getItemCount() {
    return ids.size();
  }

  @Override
  public void onBindViewHolder(
    @NonNull UserHistoryViewHolder holder, int position) {

    int id = ids.get(position);
    holder.mTextView.setText(String.valueOf(id));

  }

  public static class UserHistoryViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextView;

    public UserHistoryViewHolder(@NonNull View itemView) {
      super(itemView);
      mTextView = itemView.findViewById(R.id.tvOrganizationName);
    }
  }


}
