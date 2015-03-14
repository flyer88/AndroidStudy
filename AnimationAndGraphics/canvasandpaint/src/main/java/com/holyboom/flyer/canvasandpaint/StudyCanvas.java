package com.holyboom.flyer.canvasandpaint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by flyer on 15/3/13.
 */
public class StudyCanvas extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.study_canvas_activity);
        setContentView(new MyView(StudyCanvas.this));

        //MyView myView = new MyView(StudyCanvas.this);
    }
}
