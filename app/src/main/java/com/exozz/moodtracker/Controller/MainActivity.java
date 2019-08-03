package com.exozz.moodtracker.Controller;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.exozz.moodtracker.Model.HistoryInfos;
import com.exozz.moodtracker.Model.Mood;
import com.exozz.moodtracker.View.OnSwipeTouchListener;
import com.exozz.moodtracker.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity  {

    private ImageView mHistoryButton;
    private ImageView mCommentButton;
    private ImageView mShareButton;
    private String myComment;
    private Mood mMood;
    private HistoryInfos mHistoryInfos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHistoryInfos = new HistoryInfos(getSharedPreferences(HistoryInfos.MY_PREFS, MODE_PRIVATE));
        mHistoryInfos.myDebug();


        mShareButton = findViewById(R.id.iconShare);

        mHistoryButton = findViewById(R.id.historyButton);


        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        mCommentButton = findViewById(R.id.addCommentButton);

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Commentaire");

                final EditText weightInput = new EditText(MainActivity.this);
                weightInput.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(weightInput);


                mydialog.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myComment = weightInput.getText().toString();
                        Toast.makeText(MainActivity.this,"Commentaire ajouté !", Toast.LENGTH_LONG ).show();
                        Toast.makeText(MainActivity.this,  myComment , Toast.LENGTH_LONG ).show();

                    }
                });

                mydialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        myComment = null ;
                    }
                });

                mydialog.show();
      }
        });


        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Implement  share intent */


            }
        });

        final ConstraintLayout myLayout = findViewById(R.id.LinearLayout);
        final ImageView mySmileyFace = findViewById(R.id.SmileyFace);

        mMood = new Mood();
        myLayout.setBackgroundColor(getResources().getColor(mMood.getBackgroundColor()));
        mySmileyFace.setImageResource(mMood.getSmileyList());






        myLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                mMood.IncrementSmiley();
                myLayout.setBackgroundColor(getResources().getColor(mMood.getBackgroundColor()));
                mySmileyFace.setImageResource(mMood.getSmileyList());


            }

            public void onSwipeBottom() {
                mMood.DecrementSmiley();
                myLayout.setBackgroundColor(getResources().getColor(mMood.getBackgroundColor()));
                mySmileyFace.setImageResource(mMood.getSmileyList());
            }


            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }





    @Override
    protected void onPause() {
        super.onPause();

        Date myDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(myDate);

        mHistoryInfos.addPrefs(mMood.getChoiceList(), myComment, strDate);
        mHistoryInfos.myDebug();





    }
}

