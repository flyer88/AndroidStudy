package com.holyboom.userdefinedbroadcast2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by flyer on 15-1-20.
 */
public class AnotherBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receive another Broadcast",Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
