package tech.gruppone.stalker.app.view.recyclerview;

import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrgListItemDetailsLookup extends ItemDetailsLookup<Long> {
  RecyclerView recyclerView;

  @Nullable
  @Override
  public ItemDetails<Long> getItemDetails(@NonNull MotionEvent motionEvent) {
    View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
    if (view != null) {
      ViewHolder viewHolder = recyclerView.getChildViewHolder(view);

      return new ItemDetails<Long>() {
        @Override
        public int getPosition() {
          return viewHolder.getAdapterPosition();
        }

        @NonNull
        @Override
        public Long getSelectionKey() {
          return viewHolder.getItemId();
        }
      };
    }
    return null;
  }
}
