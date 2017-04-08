package com.svnit.harsimar.imageprocessor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class FetchImages extends AppCompatActivity {
    private String folderName;
    private RecyclerView imageRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_images);
        Bundle sentData= getIntent().getExtras();
        folderName=sentData.getString(predictions_adapter.LABEL_KEY);
        
    }
}
