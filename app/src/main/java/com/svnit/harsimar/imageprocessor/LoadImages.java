package com.svnit.harsimar.imageprocessor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoadImages extends AppCompatActivity {
    private ListView loadRecycler;
    private DatabaseReference databaseReference;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        ActionBar bar= getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("ProcessedImages");

        loadRecycler=(ListView) findViewById(R.id.load_recycler);

        String[] fetchedItems={"harsimar","sinagh","is","here"};

        ListAdapter folderAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fetchedItems);

        loadRecycler.setAdapter(folderAdapter);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
