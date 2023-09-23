package com.base.favouritelistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView text_item;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        text_item = itemView.findViewById(R.id.text_item);

    }
}
