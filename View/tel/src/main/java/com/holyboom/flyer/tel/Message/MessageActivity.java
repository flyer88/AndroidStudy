package com.holyboom.flyer.tel.Message;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.holyboom.flyer.tel.R;

/**
 * Created by flyer on 15/2/27.
 */
public class MessageActivity extends ActionBarActivity{
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_message);
        setSupportActionBar(toolbar);
    }
}
