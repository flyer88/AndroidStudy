package com.holyboom.flyer.mylaunchactivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by flyer on 15/3/12.
 */
public class mylauncharactivity extends LauncherActivity{

    String[] names =  {"设置程序参数","查看程序"};
    Class<?>[] aClass = {PreferenceActivityTest.class,ExpandableListActivityTest.class};

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(mylauncharactivity.this,aClass[position]);
    }
}
