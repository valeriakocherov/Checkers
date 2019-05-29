package com.example.checkers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Score extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> statisticList; //list score
    private ListView lvScores; // the list view
    private AdapterListView adapter;
    private AppPreference preference;
    private int stop; // num of lines in share preference

    // list view finals
    public static final String FIRST_COLUMN="Score";
    public static final String SECOND_COLUMN="Player's Name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        lvScores = (ListView)findViewById(R.id.lvScores);
        statisticList = new ArrayList<HashMap<String,String>>();
        preference = new AppPreference(getApplicationContext());

        // firs row
        HashMap<String,String> temp = new HashMap<String, String>();
        temp.put(FIRST_COLUMN,"Score");
        temp.put(SECOND_COLUMN, "player name");
        statisticList.add(temp);

        // enter data from prefernce to the list
        stop = preference.getNumLine();
        for(int i=0 ; i<stop;i++) {
            temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, preference.getScore(i)+"");
            temp.put(SECOND_COLUMN, preference.getPlayerName(i));
            statisticList.add(temp);
        }

        adapter = new AdapterListView(this, statisticList);
        lvScores.setAdapter(adapter);

    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    { //when selcting option in the menu
        // main --> go to menu
        //music --> stop/play music
        //instraction --> go to instraction
        // call --> go to phone call
        int id = item.getItemId();
        Intent intent = null;

        switch(id)
        {
            case R.id.music:
                if(MainMenu.isPlaying) {
                    MainMenu.musicService.pause();
                    item.setTitle("Unmute");
                }
                else {
                    MainMenu.musicService.resume();
                    item.setTitle("Mute");
                }
                MainMenu.isPlaying=!MainMenu.isPlaying;
                break;
            case R.id.manu_main:
                intent=new Intent(Score.this,MainMenu.class);
                startActivity(intent);
                break;

            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+""));
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                //System.exit(0);
                break;
        }
        return true;
    }


    //for music service
    @Override
    public void onResume()
    {
        super.onResume();
        if(MainMenu.isPlaying)
            MainMenu.musicService.resume();
    }
}


