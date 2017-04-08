package com.svnit.harsimar.imageprocessor;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

public class FetchImages extends AppCompatActivity {
    private String folderNameString;
    private RecyclerView imageRecycler;
    private TextView folderName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_images);
        Bundle sentData= getIntent().getExtras();
        ActionBar bar= getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        folderNameString=sentData.getString(predictions_adapter.LABEL_KEY);
        imageRecycler=(RecyclerView)findViewById(R.id.images_recycler);
        folderName=(TextView)findViewById(R.id.folder_name_tv);

        folderName.setText(folderNameString);

    }
}
