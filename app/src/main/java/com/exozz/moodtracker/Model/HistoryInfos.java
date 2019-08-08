package com.exozz.moodtracker.Model;

import android.content.SharedPreferences;
import android.util.Log;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class HistoryInfos {
    public static final String MY_PREFS = "MY_PREFS";
    private ArrayList<String> myComments ;
    private ArrayList<Integer> myMoods;
    private ArrayList<String> myDates;



    private SharedPreferences mPreferences;


    private static final String[] PREF_KEY_COMMENT_TAB = new String[]{"PREF_KEY_COMMENT1","PREF_KEY_COMMENT2",
            "PREF_KEY_COMMENT3", "PREF_KEY_COMMENT4", "PREF_KEY_COMMENT5", "PREF_KEY_COMMENT6", "PREF_KEY_COMMENT7","PREF_KEY_COMMENT8"};


    private static final String[] PREF_KEY_MOOD_TAB = new String[]{"PREF_KEY_MOOD1","PREF_KEY_MOOD2",
            "PREF_KEY_MOOD3", "PREF_KEY_MOOD4", "PREF_KEY_MOOD5", "PREF_KEY_MOOD6", "PREF_KEY_MOOD7","PREF_KEY_MOOD8"};

    private static final String[] PREF_KEY_DATE_TAB = new String[]{"PREF_KEY_DATE1","PREF_KEY_DATE2",
            "PREF_KEY_DATE3", "PREF_KEY_DATE4", "PREF_KEY_DATE5", "PREF_KEY_DATE6", "PREF_KEY_DATE7","PREF_KEY_DATE8"};


    public int getSize () {
        return myComments.size();

    }




    public HistoryInfos(SharedPreferences MyPrefs) {
        mPreferences = MyPrefs;
        myComments = new ArrayList<>();
        myMoods = new ArrayList<>() ;
        myDates = new ArrayList<>();


        getPrefs();

    }

    private void getPrefs() {



        String LastDate ;

        LastDate = mPreferences.getString(PREF_KEY_DATE_TAB[0], "");

        if ( ! LastDate.isEmpty())
        {
            Date myDate = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String strDates = dateFormat.format(myDate);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                Date strDate = sdf.parse(LastDate);
                long msDiff = Calendar.getInstance().getTimeInMillis() - strDate.getTime();
                long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
                Log.d(TAG, "HistoryInfos compare date : " + LastDate+ "");
                Log.d(TAG, "HistoryInfos compare date : " + strDates + "");
                Log.d(TAG, "HistoryInfos compare date : " + daysDiff + "");


                if ( daysDiff != 0) {
                    for (int i = 7; i >= 0; i--) {

                        int y = i - (int) daysDiff;


                        if (y >= 0 && y < 8) {
                            String test1 = mPreferences.getString(PREF_KEY_DATE_TAB[y], "");
                            mPreferences.edit().putString(PREF_KEY_DATE_TAB[i], test1).apply();

                            String test2 = mPreferences.getString(PREF_KEY_COMMENT_TAB[y], "");
                            mPreferences.edit().putString(PREF_KEY_COMMENT_TAB[i], test2).apply();

                            int test3 = mPreferences.getInt(PREF_KEY_MOOD_TAB[y], -1);
                            mPreferences.edit().putInt(PREF_KEY_MOOD_TAB[i], test3).apply();
                        } else {
                            mPreferences.edit().putString(PREF_KEY_DATE_TAB[i], "").apply();
                            mPreferences.edit().putString(PREF_KEY_COMMENT_TAB[i], "").apply();
                            mPreferences.edit().putInt(PREF_KEY_MOOD_TAB[i], -1).apply();
                        }

                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        refresh();
    }

    private void refresh() {

        myMoods.clear();
        myDates.clear();
        myComments.clear();

        for (int i = 0; i <= 7; i++) {

            myComments.add(mPreferences.getString(PREF_KEY_COMMENT_TAB[i], ""));
            myMoods.add(mPreferences.getInt(PREF_KEY_MOOD_TAB[i], -1));
            myDates.add(mPreferences.getString(PREF_KEY_DATE_TAB[i], ""));
        }
    }

    public void myDebug() {

        Log.d(TAG, "HistoryInfos = mes dates : " + myDates + "");
        Log.d(TAG, "HistoryInfos = mes moods : " + myMoods + "");
        Log.d(TAG, "HistoryInfos = comments : " + myComments + "");

    }


    public ArrayList<String> getMyComments() {
        return myComments;
    }

    public ArrayList<Integer> getMyMoods() {
        return myMoods;
    }

    public ArrayList<String> getMyDates() {
        return myDates;
    }

    public void addPrefs (int choice, String comment, String date){

    mPreferences.edit().putString(PREF_KEY_COMMENT_TAB[0], comment).apply();
    mPreferences.edit().putInt(PREF_KEY_MOOD_TAB[0], choice).apply();
    mPreferences.edit().putString(PREF_KEY_DATE_TAB[0], date).apply();
    refresh();

    }
}
