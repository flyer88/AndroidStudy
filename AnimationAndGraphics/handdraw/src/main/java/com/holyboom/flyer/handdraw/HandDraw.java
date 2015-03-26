package com.holyboom.flyer.handdraw;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class HandDraw extends ActionBarActivity {

    EmbossMaskFilter emboss;
    BlurMaskFilter blur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_draw);
        emboss = new EmbossMaskFilter(new float[]{1.5f,1.5f,1.5f}
                ,0.6f,6,4.2f);
        blur = new BlurMaskFilter(8,BlurMaskFilter.Blur.NORMAL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_hand_draw,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawView drawView = (DrawView) findViewById(R.id.draw_view);
        switch (item.getItemId()){
            case R.id.red:
                drawView.paint.setColor(Color.RED);
                break;
            case R.id.green:
                drawView.paint.setColor(Color.GREEN);
                break;
            case R.id.blue:
                drawView.paint.setColor(Color.BLUE);
                break;
            case R.id.width_1:
                drawView.paint.setStrokeWidth(1);
                break;
            case R.id.width_3:
                drawView.paint.setStrokeWidth(3);
                break;
            case R.id.width_5:
                drawView.paint.setStrokeWidth(5);
                break;
            case R.id.blur:
                drawView.paint.setMaskFilter(blur);
                break;
            case R.id.emboss:
                drawView.paint.setMaskFilter(emboss);
                break;
        }
        return true;
    }
}
