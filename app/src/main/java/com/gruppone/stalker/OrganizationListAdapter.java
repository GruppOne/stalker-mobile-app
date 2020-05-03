package com.gruppone.stalker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.gruppone.stalker.OrganizationListAdapter.OrgViewHolder;

public class OrganizationListAdapter extends ListAdapter<Organization, OrgViewHolder> {

  public static class OrgViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public OrgViewHolder(@NonNull View v) {
      super(v);
      textView = v.findViewById(R.id.organizationName);
    }

    public void bindTo(@NonNull Organization organization) {
      textView.setText(organization.getName());
    }
  }

  public OrganizationListAdapter() {
    super(
        new ItemCallback<Organization>() {
          @Override
          public boolean areItemsTheSame(
              @NonNull Organization oldOrganization, @NonNull Organization newOrganization) {
            return oldOrganization.getId() == newOrganization.getId();
          }

          @Override
          @SuppressLint("DiffUtilEquals")
          public boolean areContentsTheSame(
              @NonNull Organization oldOrganization, @NonNull Organization newOrganization) {
            return oldOrganization.equals(newOrganization);
          }
        });
  }

  @NonNull
  @Override
  public OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    return new OrgViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull OrgViewHolder holder, int position) {
    holder.bindTo(getItem(position));
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.organization_view;
  }
}
