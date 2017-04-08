package com.svnit.harsimar.imageprocessor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import clarifai2.dto.prediction.Concept;

import static com.svnit.harsimar.imageprocessor.predictions_adapter.LABEL_KEY;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    public ArrayList<String> list;
/*
    public SimpleRecyclerAdapter(ArrayList<String> labelName) {
        list=labelName;
    }*/

    public SimpleRecyclerAdapter setData(ArrayList<String> data) {
        list=data;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder holder= new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_card_row, parent, false));

        return holder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String labelString = list.get(position);
        holder.textView.setText(labelString);
        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                if(!labelString.isEmpty()){

                    Intent i=new Intent(context,FetchImages.class);
                    i.putExtra(LABEL_KEY,labelString);
                    context.startActivity(i);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView = (TextView) itemView.findViewById(R.id.card_tv);
        }
    }
}