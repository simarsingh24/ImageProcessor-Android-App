package com.svnit.harsimar.imageprocessor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class LoadImages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        ActionBar bar= getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);








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
