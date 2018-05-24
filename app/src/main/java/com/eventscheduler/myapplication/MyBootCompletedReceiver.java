package com.eventscheduler.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBootCompletedReceiver extends BroadcastReceiver {

    public void onReceive(Context paramContext, Intent paramIntent)
        {
            Log.d("boot completed receiver","called ");
            paramContext.startService(new Intent(paramContext, PowerButtonService.class));
        }
    }
