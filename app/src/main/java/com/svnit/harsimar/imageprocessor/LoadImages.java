package com.svnit.harsimar.imageprocessor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoadImages extends AppCompatActivity {
    private RecyclerView loadRecycler;
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

        loadRecycler=(RecyclerView) findViewById(R.id.load_recycler);
        loadRecycler.setHasFixedSize(true);
        loadRecycler.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                collectLabel((Map<String,Object>) dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void collectLabel(Map<String,Object> labels){
        ArrayList<String> labelName=new ArrayList<>();
        for (Map.Entry<String, Object> entry : labels.entrySet()){

            Map singleUser = (Map) entry.getValue();

            labelName.add((String) singleUser.get("label"));
        }
        Log.d("harsimarSingh",labelName.toString());

        SimpleRecyclerAdapter folderAdapter=new SimpleRecyclerAdapter();
        folderAdapter.setData(labelName);
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