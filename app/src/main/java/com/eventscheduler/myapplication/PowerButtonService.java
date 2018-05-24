package com.eventscheduler.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PowerButtonService extends Service {
    int powerbuttonclickcount=0;
    TelephonyManager telephonyManager;
    public static int phoneState;

    @Override
    public void onCreate() {
        Log.d("powerservice","calledtrue");
        MyPowerButtonReceiver myPowerButtonReceiver=new MyPowerButtonReceiver();
        IntentFilter localIntentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(myPowerButtonReceiver,localIntentFilter);
        telephonyManager= ((TelephonyManager)getSystemService(TELEPHONY_SERVICE));
        EndCallListener localEndCallListener = new EndCallListener();
        telephonyManager.listen(localEndCallListener, 32);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class EndCallListener extends PhoneStateListener
    {
        private EndCallListener()
        {
        }

        public void onCallStateChanged(int paramInt, String paramString)
        {
            if (1 == paramInt)
            {
                Log.i("PHONE", "RINGING, number: " + paramString);
                PowerButtonService.phoneState = 1;
                System.out.println("phoneState=" + PowerButtonService.phoneState);
            }
            if (2 == paramInt)
            {
                Log.i("PHONE", "OFFHOOK");
                PowerButtonService.phoneState = 1;
                Log.i("PHONE", "RINGING, number: " + PowerButtonService.phoneState);
                System.out.println("phoneState=" + PowerButtonService.phoneState);
            }
            if (paramInt == 0)
            {
                Log.i("PHONE", "IDLE");
                PowerButtonService.phoneState = 0;
                System.out.println("phoneState=" + PowerButtonService.phoneState);
            }
        }
    }
    static
    {
        phoneState = 0;
    }

}
