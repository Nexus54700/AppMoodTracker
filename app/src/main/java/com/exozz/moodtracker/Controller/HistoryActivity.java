package com.exozz.moodtracker.Controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.exozz.moodtracker.Model.HistoryInfo;
import com.exozz.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {


    private SharedPreferences mPreferences;
    private HistoryInfo mHistoryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mPreferences = getSharedPreferences(HistoryInfo.MY_PREFS, MODE_PRIVATE);
        mHistoryInfo = new HistoryInfo(mPreferences);


        final LinearLayout linearLayout = findViewById(R.id.HistoryLinear);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = linearLayout.getHeight();
                final RecyclerView rv = findViewById(R.id.historyMood);
                rv.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                rv.setAdapter(new MyAdapter(mHistoryInfo, height));

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(HistoryActivity.this);
                mLayoutManager.setReverseLayout(true);
                mLayoutManager.setStackFromEnd(true);
                rv.setLayoutManager(mLayoutManager);

            }
        });
    }
}