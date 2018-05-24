package com.eventscheduler.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyPowerButtonReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver=new MyPowerButtonReceiver();
        Intent intent=new Intent();
        intent.setAction("com.mybootcompleted");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}