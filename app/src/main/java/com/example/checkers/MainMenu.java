package com.example.checkers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity  implements View.OnClickListener{

    public static MusicService musicService; // music player
    private Intent playIntent;
    public static boolean isPlaying = true; // true if music is working else false
    private boolean musicBound;

    private Button btnPlay;
    private Button btnMusic;

    //prefernce finals
    public static final String COUNT = "COUNT";
    public static final String NAME = "NAME";
    public static final String SCORE = "SCORE";

    //    private static String gameName;
    //    public static final int SECOND_ACTIVITY = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        btnPlay = (Button) findViewById(R.id.btnplay);
        btnPlay.setOnClickListener(this);

        this.btnMusic = (Button) findViewById(R.id.btnMusic);
        this.btnMusic.setOnClickListener(this);

        musicService = new MusicService();

        musicBound = false;

        if(playIntent ==null)
        {
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection,
                    Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //when selcting option in the menu
        // main --> go to menu
        //music --> stop/play music
        //instraction --> go to instraction
        // call --> go to phone call
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.music:
                if (MainMenu.isPlaying)
                    MainMenu.musicService.pause();
                else
                    MainMenu.musicService.resume();
                MainMenu.isPlaying = !MainMenu.isPlaying;
                break;
            case R.id.manu_main:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;

            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                //System.exit(0);
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.btnplay:
                intent = new Intent(this, AddUsers.class);
                startActivity(intent);
                break;

            case R.id.btnMusic:
                intent = new Intent(this, MusicList.class);
                startActivity(intent);
                break;

            default:
                stopService(playIntent);
                musicService = null;
                finishActivity(1);
                finish();
                break;
        }

    }

    //conect to the service
    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            //get service
            musicService = binder.getService();
            // pass list
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        //shut down music service
        stopService(playIntent);
        musicService = null;
        super.onDestroy();
        System.exit(0);
    }
}

