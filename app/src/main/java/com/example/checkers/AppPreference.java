package com.example.checkers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private String playerName;
    private int score;
    private static final String APP_SHARED_PREFS = AppPreference.class.getSimpleName(); // Name of the file -.xml
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    public AppPreference(Context context)
    {
        this.pref =context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.edit = pref.edit();

        edit.putInt(MainMenu.COUNT,pref.getInt(MainMenu.COUNT,0));
        edit.apply();
    }

    public int getNumLine()
    { // num of lines of players in prefernce
        System.out.println(pref.getInt(MainMenu.COUNT,0)+" why");
        return this.pref.getInt(MainMenu.COUNT,0);
    }

    public String getPlayerName(int i)
    { // get name of player in clone i of preference
        return pref.getString(MainMenu.NAME+i,"");
    }


    public int getScore (int i)
    {
        return pref.getInt(MainMenu.SCORE+i,0);
    }

    public void update(String playerName, int score)
    {
        // update data in preference
        //if player name exsist save the hightes score
        boolean flag = false;// change to true if name was found
        int i; // loop counter, COUNT is the length of prefernce

        for(i = 0; i < pref.getInt(MainMenu.COUNT,0);i++)
        { // if player exisit
            if (pref.getString(MainMenu.NAME + i,"").equals(playerName))
                if (pref.getInt(MainMenu.SCORE + i,0) < score) {
                    edit.putInt(MainMenu.SCORE + i, score);
                    flag = true;
                }

        }


        if(!flag)
        { //player not on the list
            edit.putString(MainMenu.NAME+i ,playerName);
            edit.putInt(MainMenu.SCORE+i,score);
            if(pref.getInt(MainMenu.COUNT,0)!=0)
                edit.putInt(MainMenu.COUNT,pref.getInt(MainMenu.COUNT,0)+1);
            else
                edit.putInt(MainMenu.COUNT,1);
        }

        edit.apply();
    }

}
