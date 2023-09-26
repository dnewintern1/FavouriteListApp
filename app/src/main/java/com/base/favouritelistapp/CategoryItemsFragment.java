package com.base.favouritelistapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CategoryItemsFragment extends Fragment {





    private RecyclerView items_recycler_view;
     Category category;

    private static final String CATEGORY_ARGS= "categoryargs";

    public CategoryItemsFragment() {
        // Required empty public constructor
    }


    public static CategoryItemsFragment newInstance(Category category) {
        CategoryItemsFragment categoryItemsFragment = new CategoryItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_ARGS,category);
        categoryItemsFragment.setArguments(bundle);
        return categoryItemsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(getArguments() != null) {
            category = (Category) getArguments().getSerializable(CATEGORY_ARGS);
        }

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_category_items, container, false);
        if(view != null){
            items_recycler_view = view.findViewById(R.id.items_recycler_view);
            items_recycler_view.setAdapter(new ItemsRecyclerAdapter(category));
            items_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }

    public void addItemToCategory(String item){

        category.getItems().add(item);

        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) items_recycler_view.getAdapter();

        itemsRecyclerAdapter.setCategory(category);
        itemsRecyclerAdapter.notifyDataSetChanged();

    }


}