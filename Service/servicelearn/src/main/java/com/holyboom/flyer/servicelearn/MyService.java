package com.holyboom.flyer.servicelearn;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by flyer on 15/1/30.
 * MyService 可以和任何activity绑定
 * 同样绑定后可以获得其DownloadBinder的方法
 */
public class MyService extends Service{

    DownloadBinder downloadBinder = new DownloadBinder();

    /**
     *
     */
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.e("Myservice","startDownload 运行");
        }
        public void getProgress(){
            Log.e("Myservice","getProgress 运行");
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    /**
     * 服务创建是调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        /*将后台服务变成一个前台服务*/
        Notification notification = new
                Notification(R.drawable.ic_launcher,"notification",System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        notification.setLatestEventInfo(this,"title","content",pendingIntent);
        startForeground(1,notification);
        Log.e("onCreate","服务创建");
    }

    /**
     * 服务启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*建议使用线程开启，防止ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }).start();*/
        Log.e("onStartCommand","服务开启");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时调用
     */
    @Override
    public void onDestroy() {
        Log.e("onDestory","服务销毁");
        super.onDestroy();
    }
}
