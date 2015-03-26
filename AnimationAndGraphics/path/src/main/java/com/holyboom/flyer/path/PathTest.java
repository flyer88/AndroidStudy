package com.holyboom.flyer.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by flyer on 15/3/13.
 */
public class PathTest extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new MyView(this));
        setContentView(new TextView(this));
    }
    class TextView extends View{

        final String DRAW_STR = "flyer88";
        Path[] paths = new Path[3];
        Paint paint;

        public TextView(Context context) {
            super(context);
            paths[0] = new Path();
            paths[0].moveTo(0,0);
            for (int i = 1;i<=7;i++){
                paths[0].lineTo(i*50, (float) (Math.random()*50));
            }
            paths[1] = new Path();
            RectF rectF = new RectF(0,0,200,120);
            paths[1].addOval(rectF,Path.Direction.CCW);
            paths[2] = new Path();
            paths[2].addArc(rectF,60,180);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(1);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            canvas.translate(40,40);
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setTextSize(50);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[0],paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR,paths[0],0,0,paint);
            canvas.translate(0,60);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[1],paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR,paths[1],-20,20,paint);
            canvas.translate(0,120);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[2],paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR,paths[2],-25,20,paint);
        }
    }
}
