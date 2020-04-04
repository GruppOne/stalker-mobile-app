package com.gruppone.stalker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrganizationListAdapter extends RecyclerView.Adapter<OrganizationListAdapter.MyViewHolder> {
  private String[] mDataset;

  public static class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public MyViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  public OrganizationListAdapter(ArrayList<String> dataset) {
    mDataset = dataset.toArray(new String[dataset.size()]);
  }

  @NonNull
  @Override
  public OrganizationListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView v = (TextView) LayoutInflater.from(parent.getContext())
                                          .inflate(R.layout.organization_view,
                                                   parent, false);
    MyViewHolder vh = new MyViewHolder(v);
    return vh;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.textView.setText(mDataset[position]);
  }

  @Override
  public int getItemCount() {
    return mDataset.length;
  }
}
