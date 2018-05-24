package com.eventscheduler.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;

public class MyPowerButtonReceiver extends BroadcastReceiver
    {
        private static int countPowerOff = 0;

        public MyPowerButtonReceiver()
        {

        }

        @Override
        public void onReceive(Context context, Intent intent)
        {
           Log.d("powerbtrecivercalled","true");
            if ((PowerButtonService.phoneState == 0) && intent.getAction().equals(Intent.ACTION_SCREEN_OFF)||intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                if(countPowerOff==0)
                    new CountDownTimer(3000L, 1000L) {
                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {
                            MyPowerButtonReceiver.countPowerOff = 0;
                         }
                    }.start();

                countPowerOff++;
                Log.e("In on receive", "In Method:  ACTION_USER_PRESENT");
                if ((countPowerOff == 3) && (PowerButtonService.phoneState == 0))
                {
                    Log.d("4timespowerclick","true");
                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);
                    Toast.makeText(context, "MAIN ACTIVITY IS BEING CALLED ", Toast.LENGTH_LONG).show();
                    Intent intent1=new Intent(context,MainActivity.class);
                    context.startActivity(intent1);
                    Intent localIntent1 = new Intent();
                    localIntent1.setAction(Intent.ACTION_CALL);
                    localIntent1.setData(Uri.parse("tel:04071011800"));
                    localIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(localIntent1);
                    countPowerOff=0;
                }

            }
        }
    }





