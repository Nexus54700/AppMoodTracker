package com.exozz.moodtracker.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exozz.moodtracker.Model.HistoryInfos;
import com.exozz.moodtracker.R;


import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


   private HistoryInfos mDataSet;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataSet.getSize();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.timeOfMood);
        }



    }

    public void setDataSet(HistoryInfos dataset) {
        mDataSet = dataset;
    }

    public MyAdapter(HistoryInfos myDataSet) {
        mDataSet = myDataSet;
    }
}
