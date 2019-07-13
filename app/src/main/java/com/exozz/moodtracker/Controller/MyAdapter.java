package com.exozz.moodtracker.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exozz.moodtracker.Model.HistoryInfos;
import com.exozz.moodtracker.R;



class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


   private HistoryInfos mDataSet;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_history, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        mDataSet.getMyComments().get(position);
        myViewHolder.mTextView.setText("");

    } // recuperer la position et les valeurs a modifier

    @Override
    public int getItemCount() {
        return mDataSet.getSize();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout mLinearLayout;
        public ImageView mImageView;
        public TextView mTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.timeOfMood);
            mImageView = itemView.findViewById(R.id.iconComment);
            mLinearLayout = itemView.findViewById(R.id.layoutHistoryMood);




        }

    }

    public void setDataSet(HistoryInfos dataset) {
        mDataSet = dataset;
    }

    public MyAdapter(HistoryInfos myDataSet) {
        mDataSet = myDataSet;
    }
}
