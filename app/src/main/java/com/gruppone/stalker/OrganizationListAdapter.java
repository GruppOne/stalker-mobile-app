package com.gruppone.stalker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gruppone.stalker.OrganizationListAdapter.OrgViewHolder;
import java.util.List;

public class OrganizationListAdapter extends RecyclerView.Adapter<OrgViewHolder> {

  private String[] mDataset;

  public static class OrgViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public OrgViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  public OrganizationListAdapter(List<Organization> dataset) {
    mDataset = dataset.toArray(new String[dataset.size()]);
  }

  @NonNull
  @Override
  public OrgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView v = (TextView) LayoutInflater.from(parent.getContext())
      .inflate(R.layout.organization_view,
        parent, false);
    OrgViewHolder vh = new OrgViewHolder(v);
    return vh;
  }

  @Override
  public void onBindViewHolder(OrgViewHolder holder, int position) {
    holder.textView.setText(mDataset[position]);
  }

  @Override
  public int getItemCount() {
    return mDataset.length;
  }
}
