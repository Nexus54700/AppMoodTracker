package com.exozz.moodtracker.Controller;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exozz.moodtracker.Model.HistoryInfos;
import com.exozz.moodtracker.Model.Mood;
import com.exozz.moodtracker.R;

import java.util.ArrayList;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {





    /*static final String[] PREF_KEY_DAY_TAB = new String[]{"Hier",
            "Avant hier", "Il y a 3 jours", "Il y a 4 jours", "Il y a 5 jours", "Il y a 6 jours", "Il y a une semaine"};*/

    static final String[] PREF_KEY_DAY_TAB = new String[]{"Il y a une semaine", "Il y a 6 jours", "Il y a 5 jours", "Il y a 4 jours", "Il y a 3 jours", "Avant hier","Hier"};


   private HistoryInfos mDataSet;
   private Mood mMood;




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_history, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

       if (mDataSet.getMyMoods().get(position) != -1) {

           myViewHolder.mLinearLayout.setVisibility(View.VISIBLE);

           mDataSet.getMyDates().get(position);
           myViewHolder.mTextView.setText(PREF_KEY_DAY_TAB[position]);

           mDataSet.getMyComments().get(position);

           if (mDataSet.getMyComments().isEmpty()) {
               myViewHolder.mImageView.setVisibility(View.INVISIBLE);
           } else
               {
                   myViewHolder.mImageView.setVisibility(View.VISIBLE);
                   myViewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Toast.makeText(view.getContext(),mDataSet.getMyComments().get(position), Toast.LENGTH_LONG ).show();

                       }
                   });
               }

           myViewHolder.mLinearLayout.setWeightSum(mDataSet.getMyMoods().set(3, position));
           int color = mMood.getBackgroundColors().get(mDataSet.getMyMoods().get(position));

           myViewHolder.mRelativeLayout.setBackgroundColor(ContextCompat.getColor(myViewHolder.itemView.getContext(), color)); // essaie de recuperé la valeur du mood pour changer le background de la list

       }
       else
           {
           myViewHolder.mLinearLayout.setVisibility(View.INVISIBLE);
            }

    } // recuperer la position et les valeurs a modifier






    @Override
    public int getItemCount() {
        return mDataSet.getSize();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout mLinearLayout;
        public ImageView mImageView;
        public TextView mTextView;
        public RelativeLayout mRelativeLayout;
        public ImageView commentButton;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.timeOfMood);
            mImageView = itemView.findViewById(R.id.iconComment);
            mLinearLayout = itemView.findViewById(R.id.layoutHistoryMood);
            mRelativeLayout = itemView.findViewById(R.id.RelativeLayoutHistory);
            commentButton = itemView.findViewById(R.id.iconComment);


        }

    }

    public void setDataSet(HistoryInfos dataset) {
        mDataSet = dataset;
    }

    public MyAdapter(HistoryInfos myDataSet) {
        mDataSet = myDataSet;
        mMood = new Mood();
    }
}
