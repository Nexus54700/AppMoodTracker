package com.exozz.moodtracker.Model;


import com.exozz.moodtracker.R;

import java.util.ArrayList;

public class Mood {

    private int ChoiceList ;
    private final ArrayList<Integer>  smileyList;
    private final ArrayList<Integer> backgroundColor;


    public Mood() {
        ChoiceList = 1;
    smileyList = new ArrayList<>(5);
        smileyList.add(R.mipmap.smiley_super_happy);
        smileyList.add(R.mipmap.smiley_happy);
        smileyList.add(R.mipmap.smiley_normal);
        smileyList.add(R.mipmap.smiley_disappointed);
        smileyList.add(R.mipmap.smiley_sad);


    backgroundColor = new ArrayList<>(5) ;
        backgroundColor.add(R.color.banana_yellow);
        backgroundColor.add(R.color.light_sage);
        backgroundColor.add(R.color.cornflower_blue_65);
        backgroundColor.add(R.color.warm_grey);
        backgroundColor.add(R.color.faded_red);

    }

    public ArrayList<Integer> getBackgroundColors() {return backgroundColor; }



    public Integer getSmileyList() {
        return smileyList.get(ChoiceList);
    }

    public Integer getBackgroundColor() {
        return backgroundColor.get(ChoiceList);
    }

    public void IncrementSmiley (){

        if (ChoiceList > 0 ) {
            ChoiceList--;
        }

    }

    public void DecrementSmiley (){
        if (ChoiceList < 4) {
            ChoiceList++;
        }



    }

    public int getChoiceList (){
        return ChoiceList;
    }


}
