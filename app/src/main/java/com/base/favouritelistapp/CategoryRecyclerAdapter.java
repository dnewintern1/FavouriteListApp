package com.base.favouritelistapp;

import android.annotation.SuppressLint;
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

    interface CategoryIsClickedInterface{

        void categoryIsClicked(Category category);


    }

    private CategoryIsClickedInterface categoryIsClickedListener;

  //  String[] catagories = {"hobbies","Electronic gadget", "games", "food", "Counteries", "sport"};

    private   ArrayList<Category> categories;

    public CategoryRecyclerAdapter(ArrayList<Category> categories, CategoryIsClickedInterface categoryIsClickedListener) {

        this.categories = categories;
        this.categoryIsClickedListener = categoryIsClickedListener;
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
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.getCategory_no_txtV().setText(Integer.toString(position + 1));
        holder.getCategory_name_txt().setText(categories.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryIsClickedListener.categoryIsClicked(categories.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  void    addCategory(Category category){

        categories.add(category);

        notifyItemInserted(categories.size()-1);


    }


}
