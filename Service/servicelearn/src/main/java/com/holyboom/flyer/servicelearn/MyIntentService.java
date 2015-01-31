package com.holyboom.flyer.servicelearn;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by flyer on 15/1/31.
 */
public class MyIntentService extends IntentService{

    public MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        /*此处已经是另开的线程
        * 可以用于处理具体的逻辑事物
        * */
        Log.e("MyIntentService","Thread id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyIntentService","onDestroy() is executed");
    }
}
