package com.base.favouritelistapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryRecyclerAdapter.CategoryIsClickedInterface {

    private RecyclerView catagory_recyclerView;


    private  CategoryManager mCategoryManager;



    public CategoryManager getmCategoryManager() {
        return mCategoryManager;
    }



    @Override
    public void categoryIsClicked(Category category) {

        listenerObject.categoryIsTapped(category);

    }

    interface onCategoryInteractionListener {

        void  categoryIsTapped(Category category);
    }

    private onCategoryInteractionListener listenerObject;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


            if(context instanceof onCategoryInteractionListener){

                listenerObject =(onCategoryInteractionListener) context;

                mCategoryManager = new CategoryManager(context);

            } else {
                throw new RuntimeException("Hey Devloper. The Context or activity must implement the onCategoryInteractionListener");

            }
    }

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {

        return  new CategoryFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ArrayList<Category> categories =mCategoryManager.retrieveCategories();
        catagory_recyclerView = getView().findViewById(R.id.items_recycler_view);
        if(getView() !=null) {
            catagory_recyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));
            catagory_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);


    }

    @Override
    public void onDetach() {
        super.onDetach();

        listenerObject = null;
    }

    public void giveCategoryToManager(Category category){

        mCategoryManager.saveCategory(category);

        CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) catagory_recyclerView.getAdapter();
         categoryRecyclerAdapter.addCategory(category);

    }
    public  void saveCategory(Category category){

        mCategoryManager.saveCategory(category);

        updateRecyclerView();
    }

    private void updateRecyclerView() {

        ArrayList<Category> categories = mCategoryManager.retrieveCategories();

        //code watchout*******************88

        catagory_recyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));

    }
}