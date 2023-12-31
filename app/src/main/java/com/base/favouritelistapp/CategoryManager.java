package com.base.favouritelistapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.prefs.Preferences;

public class CategoryManager {

    private Context mContext;

    public CategoryManager(Context context) {
        mContext = context;
    }

    public void  saveCategory(@NonNull Category category){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        HashSet itemsHashSet = new HashSet(category.getItems());
        editor.putStringSet(category.getName(), itemsHashSet);

        editor.apply();



    }

    public ArrayList<Category> retrieveCategories(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        Map<String,?>data =sharedPreferences.getAll();

        ArrayList<Category> categories = new ArrayList<>();
        for(Map.Entry<String, ?>entry : data.entrySet()){
            Category category=new Category(entry.getKey(),new ArrayList<String>((HashSet)entry.getValue()));

            categories.add(category);

        }
        return categories;

    }
}
