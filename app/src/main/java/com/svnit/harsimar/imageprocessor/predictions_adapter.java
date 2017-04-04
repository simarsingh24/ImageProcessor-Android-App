package com.svnit.harsimar.imageprocessor;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import clarifai2.dto.prediction.Concept;

public class predictions_adapter extends RecyclerView.Adapter<predictions_adapter.ViewHolder> {

    public List<Concept> list;

    /*public predictions_adapter(List<Concept> list) {
        this.list=list;
    }*/

    public predictions_adapter setData(List<Concept> concepts) {
        list=concepts;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_predictions, parent, false));


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Concept concept = list.get(position);
        holder.predictions.setText(concept.name() != null ? concept.name() : concept.id());
        holder.percentage.setText(String.valueOf(concept.value()));
    }

    @Override
    public int getItemCount() {
        //      return list.size();
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView predictions;
        TextView percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            predictions = (TextView) itemView.findViewById(R.id.predictions_tv);
            percentage = (TextView) itemView.findViewById(R.id.predictions_percentage_tv);

        }
    }
}