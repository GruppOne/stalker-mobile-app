package tech.gruppone.stalker.app.view.recyclerview;

import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Setter;
import tech.gruppone.stalker.app.R;
import tech.gruppone.stalker.app.business.Organization;
import tech.gruppone.stalker.app.view.OrganizationActivity;
import tech.gruppone.stalker.app.view.recyclerview.OrganizationListAdapter.OrgViewHolder;

public class OrganizationListAdapter extends ListAdapter<LiveData<Organization>, OrgViewHolder> {

  public static class OrgViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    private int id;
    private TextView name;
    private TextView description;
    private TextView privatePublicIcon;

    private Context context;

    private View view;

    public OrgViewHolder(@NonNull View v) {
      super(v);
      view = v;
      name = v.findViewById(R.id.organizationName);
      description = v.findViewById(R.id.organizationDescription);
      privatePublicIcon = v.findViewById(R.id.privatePublicIcon);

      v.setOnClickListener(this);

      context = v.getContext();
    }

    public void bindTo(@NonNull LiveData<Organization> newOrganizationLiveData, boolean active) {
      Organization organization = requireNonNull(newOrganizationLiveData.getValue());
      id = organization.getId();
      name.setText(organization.getName());
      description.setText(organization.getDescription());
      privatePublicIcon.setCompoundDrawablesRelativeWithIntrinsicBounds(
          organization.isPrivate()
              ? R.drawable.ic_lock_black_24dp
              : R.drawable.ic_lock_open_black_24dp,
          0,
          0,
          0);

      view.setActivated(active);
    }

    @Override
    public void onClick(@NonNull View v) {
      Intent intent = new Intent(context, OrganizationActivity.class);
      intent.putExtra("tech.gruppone.stalker.app.organizationId", id);
      context.startActivity(intent);
    }
  }

  @Setter private SelectionTracker<Long> selectionTracker = null;

  public OrganizationListAdapter() {
    super(
        new ItemCallback<LiveData<Organization>>() {
          @Override
          public boolean areItemsTheSame(
              @NonNull LiveData<Organization> oldItem, @NonNull LiveData<Organization> newItem) {
            return oldItem == newItem;
          }

          @Override
          @SuppressLint("DiffUtilEquals")
          public boolean areContentsTheSame(
              @NonNull LiveData<Organization> oldItem, @NonNull LiveData<Organization> newItem) {
            return requireNonNull(oldItem.getValue()).equals(newItem.getValue());
          }
        });

    setHasStableIds(true);
  }

  @NonNull
  @Override
  public OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    return new OrgViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull OrgViewHolder holder, int position) {
    holder.bindTo(
        getItem(position),
        selectionTracker != null && selectionTracker.isSelected(holder.getItemId()));
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.organization_view;
  }

  @Override
  public long getItemId(int position) {
    return requireNonNull(getItem(position).getValue()).getId();
  }
}
