package com.holyboom.flyer.servicelearn;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by flyer on 15/1/30.
 */
public class MyService extends Service{

    DownloadBinder downloadBinder = new DownloadBinder();
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
