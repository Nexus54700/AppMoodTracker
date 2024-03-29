package com.exozz.moodtracker.Controller;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exozz.moodtracker.BuildConfig;
import com.exozz.moodtracker.Model.HistoryInfo;
import com.exozz.moodtracker.Model.Mood;
import com.exozz.moodtracker.R;

import static android.content.ContentValues.TAG;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private static final String[] PREF_KEY_DAY_TAB = new String[]{"Hier",
            "Avant hier", "Il y a trois jours", "Il y a quatre jours", "Il y a cinq jours", "Il y a six jours", "Il y a une semaine"};

    private HistoryInfo mDataSet;
    private Mood mMood;
    private int mHeightSize;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_history, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "pos = " + position + " " + mDataSet.getMyMoods().get(position + 1) + "");
            Log.d(TAG, "pos = " + position + " " + mDataSet.getMyDates().get(position + 1) + "");
            Log.d(TAG, "pos = " + position + " " + mDataSet.getMyComments().get(position + 1) + "");
        }

        if (mDataSet.getMyMoods().get(position + 1) != -1) {

            myViewHolder.mLinearLayout.setVisibility(View.VISIBLE);


            mDataSet.getMyDates().get(position + 1);
            myViewHolder.mTextView.setText(PREF_KEY_DAY_TAB[position]);

            mDataSet.getMyComments().get(position + 1);


            if (mDataSet.getMyComments().get(position + 1).isEmpty()) {
                myViewHolder.mImageView.setVisibility(View.INVISIBLE);
            } else {

                myViewHolder.mImageView.setVisibility(View.VISIBLE);
                myViewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), mDataSet.getMyComments().get(position + 1), Toast.LENGTH_LONG).show();

                    }
                });


            }


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, RelativeLayout.LayoutParams.MATCH_PARENT, 5 - mDataSet.getMyMoods().get(position + 1));

            myViewHolder.mRelativeLayout.setLayoutParams(lp);


            int color = mMood.getBackgroundColors().get(mDataSet.getMyMoods().get(position + 1));

            myViewHolder.mRelativeLayout.setBackgroundColor(ContextCompat.getColor(myViewHolder.itemView.getContext(), color));


        } else {
            myViewHolder.mLinearLayout.setVisibility(View.INVISIBLE);
        }


        final int sizePhone = mHeightSize / 7;

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, sizePhone, 0);
        myViewHolder.mLinearLayout.setLayoutParams(lp2);
    }


    @Override
    public int getItemCount() {
        return mDataSet.getSize() - 1;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public ImageView mImageView;
        public ImageView mShare;
        public TextView mTextView;
        public RelativeLayout mRelativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.timeOfMood);
            mImageView = itemView.findViewById(R.id.iconComment);
            mShare = itemView.findViewById(R.id.iconShare);
            mLinearLayout = itemView.findViewById(R.id.layoutHistoryMood);
            mRelativeLayout = itemView.findViewById(R.id.RelativeLayoutHistory);


        }

    }

    public void setDataSet(HistoryInfo dataSet) {
        mDataSet = dataSet;
    }

    public MyAdapter(HistoryInfo myDataSet, int statusSize) {
        mDataSet = myDataSet;
        mMood = new Mood();
        mHeightSize = statusSize;
    }
}
