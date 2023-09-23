package com.base.favouritelistapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.favouritelistapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryRecyclerAdapter.CategoryIsClickedInterface {

    public  static final String CATEGORY_OBJECT_KEY = "CATEGORY_KEY";
    public  static final int MAIN_ACTIVITY_REQUEST_CODE = 1000;

    private RecyclerView catagory_recyclerView;
    private ActivityMainBinding binding;

    private  CategoryManager mCategoryManager=new CategoryManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        ArrayList<Category> categories =mCategoryManager.retrieveCategories();
        catagory_recyclerView = findViewById(R.id.catagory_recyclerView);

        catagory_recyclerView.setAdapter(new CategoryRecyclerAdapter(categories,this));
        catagory_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "fab is clicked",Toast.LENGTH_SHORT).show();

                displayCreateCategoryDialog();
            }
        });


    }
    private void displayCreateCategoryDialog(){
        String alertTitle = String.valueOf(R.string.create_Cat);
        String positiveButtonTitle ="Create";
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        EditText  categoryEditText =   new EditText(this);
        categoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        alertBuilder.setTitle(alertTitle);
        alertBuilder.setView(categoryEditText);
        alertBuilder.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Category category = new Category(categoryEditText.getText().toString(), new ArrayList<String>() );
                mCategoryManager.saveCategory(category);

                CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) catagory_recyclerView.getAdapter();
                categoryRecyclerAdapter.addCategory(category);

                dialogInterface.dismiss();
                displayCategoryItems(category);


            }
        });
        alertBuilder.create().show();

    }

    private  void displayCategoryItems(Category category){

        Intent categoryItemsIntent = new Intent(this,CategoryItemsActivity.class);
        categoryItemsIntent.putExtra(CATEGORY_OBJECT_KEY,category);

       startActivityForResult(categoryItemsIntent,MAIN_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MAIN_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data!=null){
                mCategoryManager.saveCategory((Category) data.getSerializableExtra(CATEGORY_OBJECT_KEY));
                updateCategories();
            }
        }
    }

    private void updateCategories() {

        ArrayList<Category> categories =  mCategoryManager.retrieveCategories();
        catagory_recyclerView.setAdapter(new CategoryRecyclerAdapter(categories,this));

    }

    @Override
    public void categoryIsClicked(Category category) {
        displayCategoryItems(category);
    }
}