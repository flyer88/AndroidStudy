package com.holyboom.flyer.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by flyer on 15/1/31.
 */
public class LongRunningService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("LongRunningService","executed at "+new Date().toString());
            }
        }).start();

        int anHour = 60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        AlarmManager  alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent mIntent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,mIntent,0);
        //alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,triggerAtTime,pendingIntent);
        //4.4后用此方法会更为准确
        alarmManager.set(AlarmManager.ELAPSED_REALTIME,triggerAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
