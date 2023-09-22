package com.base.favouritelistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView category_no_txtV,category_name_txt;
    public CategoryViewHolder(@NonNull View View) {
        super(View );
        category_name_txt = itemView.findViewById(R.id.category_name_txt);
        category_no_txtV = itemView.findViewById(R.id.category_no_txtV);

    }

    public TextView getCategory_no_txtV() {
        return category_no_txtV;
    }

    public TextView getCategory_name_txt() {
        return category_name_txt;
    }
}
