package com.holyboom.flyer.servicebestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by flyer on 15/1/31.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mIntent = new Intent(context,LongRunningService.class);
        context.startService(mIntent);
    }
}
