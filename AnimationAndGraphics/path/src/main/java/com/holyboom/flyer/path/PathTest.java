package com.holyboom.flyer.path;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by flyer on 15/3/13.
 */
public class PathTest extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
