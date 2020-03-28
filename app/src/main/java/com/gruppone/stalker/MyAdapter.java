package com.gruppone.stalker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
  private String[] mDataset;

  public static class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public MyViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  public MyAdapter(String[] myDataset) {
    mDataset = myDataset;
  }

  @Override
  public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
