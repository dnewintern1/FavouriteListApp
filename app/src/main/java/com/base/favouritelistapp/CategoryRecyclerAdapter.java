package com.base.favouritelistapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kotlin.collections.IntIterator;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

  //  String[] catagories = {"hobbies","Electronic gadget", "games", "food", "Counteries", "sport"};

    private ArrayList<Category> catagories;

    public CategoryRecyclerAdapter(ArrayList<Category> catagories) {
        this.catagories = catagories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.catagory_viewholder,parent,false);

        CategoryViewHolder categoryViewHolder= new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.getCategory_no_txtV().setText(Integer.toString(position + 1));
        holder.getCategory_name_txt().setText(catagories[position]);
    }

    @Override
    public int getItemCount() {
        return catagories.length;
    }
}
