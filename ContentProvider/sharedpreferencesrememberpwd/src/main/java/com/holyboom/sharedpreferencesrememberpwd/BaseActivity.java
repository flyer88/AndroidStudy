package com.holyboom.sharedpreferencesrememberpwd;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by flyer on 15-1-24.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
