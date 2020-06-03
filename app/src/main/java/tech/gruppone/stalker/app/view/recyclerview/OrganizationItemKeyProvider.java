package tech.gruppone.stalker.app.view.recyclerview;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class OrganizationItemKeyProvider extends ItemKeyProvider<Long> {

  private RecyclerView recyclerView;

  public OrganizationItemKeyProvider(@NonNull RecyclerView recyclerView) {
    super(ItemKeyProvider.SCOPE_MAPPED);
    this.recyclerView = recyclerView;
  }

  @NonNull
  @Override
  public Long getKey(int position) {
    return requireNonNull(recyclerView.getAdapter()).getItemId(position);
  }

  @Override
  public int getPosition(@NonNull Long key) {
    ViewHolder viewHolder = recyclerView.findViewHolderForItemId(key);

    return viewHolder != null ? viewHolder.getLayoutPosition() : RecyclerView.NO_POSITION;
  }
}
