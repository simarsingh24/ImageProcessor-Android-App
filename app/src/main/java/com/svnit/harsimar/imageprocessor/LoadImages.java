package com.svnit.harsimar.imageprocessor;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class LoadImages extends AppCompatActivity {
    private RecyclerView loadRecycler;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<imageData,ImageViewHolder> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<imageData, ImageViewHolder>(
                        imageData.class,
                        R.layout.load_image_row_layout,
                        ImageViewHolder.class,
                        databaseReference)
                {
                    @Override
                    protected void populateViewHolder(ImageViewHolder viewHolder, imageData model, int position) {
                        viewHolder.setImageLabel(model.getLabel());
                        viewHolder.setImage(getApplicationContext(),model.getImageLink());
                        viewHolder.setLoctation("chandigarh");
                    }
                };
        loadRecycler.setAdapter(firebaseRecyclerAdapter);

    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setImageLabel(String label){
            TextView imageLabel =(TextView)mView.findViewById(R.id.label_tv);
            imageLabel.setText(label);
        }
        public void setLoctation(String location){
            TextView imgLocation =(TextView)mView.findViewById(R.id.location_tv);
            imgLocation.setText(location);
        }

        public void setImage(Context context, String imageUri){
            final ImageView photo=(ImageView)mView.findViewById(R.id.image_load_iv);
            Picasso.with(context).load(imageUri).into(photo);

        }

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