package com.exozz.moodtracker.Controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exozz.moodtracker.Model.HistoryInfos;
import com.exozz.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView; // recyclerview dans activity_history ( historymood )
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SharedPreferences mPreferences;


    private HistoryInfos mHistoryInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mPreferences = getSharedPreferences(HistoryInfos.MY_PREFS, MODE_PRIVATE);
        mHistoryInfos = new HistoryInfos(mPreferences);

        final RecyclerView rv = findViewById(R.id.historyMood);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(mHistoryInfos));

    }
}
