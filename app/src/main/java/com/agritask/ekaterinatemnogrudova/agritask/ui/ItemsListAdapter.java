package com.agritask.ekaterinatemnogrudova.agritask.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.agritask.ekaterinatemnogrudova.agritask.BR;
import com.agritask.ekaterinatemnogrudova.agritask.R;
import com.agritask.ekaterinatemnogrudova.agritask.models.Item;
import java.util.List;
import static com.agritask.ekaterinatemnogrudova.agritask.utils.Constants.DIGITS_LIMIT;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.BindingHolder>  {
    private List<Item> mItems;

    public ItemsListAdapter(List<Item> films) {
        mItems = films;
    }

    @Override
    public ItemsListAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewDataBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_view_item, parent, false);

        ItemsListAdapter.BindingHolder holder = new ItemsListAdapter.BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemsListAdapter.BindingHolder holder, final int position) {
        Item item = mItems.get(position);
        holder.getBinding().setVariable(BR.item, item);
        holder.getBinding().setVariable(BR.isItemIdLessThanLimit, isItemIdLessThanLimit(item.getId()));
        holder.getBinding().setVariable(BR.isEvenItem, isEvenItem(position));
        holder.getBinding().executePendingBindings();
    }

    private boolean isItemIdLessThanLimit(int id){
        return  (String.valueOf(id).length() <= DIGITS_LIMIT);
    }

    private boolean isEvenItem(int position){
        return  (position % 2 == 0);
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }

    class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
        }

        ViewDataBinding getBinding() {
            return binding;
        }

        void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}
