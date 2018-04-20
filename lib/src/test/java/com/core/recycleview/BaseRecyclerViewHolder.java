package com.core.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V getItemView() {
        return (V) itemView;
    }
}
