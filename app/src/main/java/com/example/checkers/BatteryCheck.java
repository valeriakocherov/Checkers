package com.example.checkers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class BatteryCheck extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get Battery
        int level = intent.getIntExtra("level", 0);

        // present low buttery to the user. in 95 present to see if works
        if(level >10)
        {
            timerDelayRemoveDialog(3000,new AlertDialog.Builder(context).setTitle("LowButtery").setIcon(android.R.drawable.ic_lock_idle_low_battery).show());
        }
    }

    public void timerDelayRemoveDialog(long time, final Dialog d)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                d.dismiss();
            }},time);
    }


}
