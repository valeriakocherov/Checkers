package com.example.checkers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUsers extends AppCompatActivity {
    private EditText white, black;
    private Button play;
    private String whitestr, blackstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);


        white = (EditText) findViewById(R.id.whiteName);

        black = (EditText) findViewById(R.id.blackName);

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whitestr = white.getText().toString();
                blackstr = black.getText().toString();

                openCheckers();
            }
        });

    }

    public void openCheckers(){
        Intent intent = new Intent(this, Checkers.class);
        intent.putExtra("whitePlayer", whitestr);
        intent.putExtra("blackPlayer", blackstr);
//        int playerXpoints = 0;
//        intent.putExtra("playerXpoints", Integer.toString(playerXpoints));
//        int playerOpoints = 0;
//        intent.putExtra("playerOpoints", Integer.toString(playerOpoints));
        startActivity(intent);
        finish();
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
}

