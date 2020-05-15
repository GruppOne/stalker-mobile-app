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
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.utility.OrganizationListAdapter.OrgViewHolder;

public class OrganizationListAdapter extends ListAdapter<Organization, OrgViewHolder> {

  public static class OrgViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView description;
    private TextView privatePublicIcon;

    public OrgViewHolder(@NonNull View v) {
      super(v);
      name = v.findViewById(R.id.organizationName);
      description = v.findViewById(R.id.organizationDescription);
      privatePublicIcon = v.findViewById(R.id.privatePublicIcon);
    }

    public void bindTo(@NonNull Organization organization) {
      name.setText(organization.getName());
      description.setText(organization.getDescription());
      privatePublicIcon.setCompoundDrawablesRelativeWithIntrinsicBounds(
          organization.isPrivate()
              ? R.drawable.ic_lock_black_24dp
              : R.drawable.ic_lock_open_black_24dp,
          0,
          0,
          0);
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
