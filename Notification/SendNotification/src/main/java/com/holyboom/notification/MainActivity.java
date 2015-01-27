package com.holyboom.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button sendNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotification = (Button)findViewById(R.id.send_notification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                /**
                 * 第一个参数为图片
                 * 第二个参数为一闪而过显示的内容
                 * 第三个参数显示通知时间
                 * lolipop中不推荐，ticker内容不会被显示
                 */
                Notification notification = new Notification(R.drawable.ic_launcher,"This is a ticker text",System.currentTimeMillis());
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                /**
                 * 第一个参数context
                 * 第二个参数为标题
                 * 第三个参数为内容
                 * 第四个参数PendingIntent,即点击采取的intent
                 */
                notification.setLatestEventInfo(MainActivity.this,"This is content title","This is content text",pendingIntent);
                /**
                 * 显示通知
                 * 第一个为通知id
                 * 第二个为notification对象
                 */
                manager.notify(1,notification);
            }
        });


    }
}
