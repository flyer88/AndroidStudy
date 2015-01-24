package com.holyboom.sharedpreferencesrememberpwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by flyer on 15-1-23.
 */
public class MainActivity extends BaseActivity{

    Button forceToOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forceToOffline = (Button)findViewById(R.id.force_to_offline);
        forceToOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.holyboom.FORCE_TO_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
