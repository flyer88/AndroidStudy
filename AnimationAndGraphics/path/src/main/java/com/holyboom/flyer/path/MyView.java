package com.holyboom.flyer.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.view.View;

/**
 * Created by flyer on 15/3/13.
 */
public class MyView extends View{

    float phase;
    PathEffect [] effects = new PathEffect[7];
    int[] colors;
    Paint paint;
    Path path;


    public MyView(Context context) {
        super(context);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);

        path = new Path();
        path.moveTo(0,0);
        for (int i = 1;i<=15;i++){
            path.lineTo(i*20, (float) (Math.random()*60));
        }
        colors = new int[]{
                Color.BLUE,Color.DKGRAY,Color.YELLOW,
                Color.GREEN,Color.GRAY,Color.CYAN,Color.MAGENTA
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        effects[0] = null;
        effects[1] = new CornerPathEffect(10);
        effects[2] = new DiscretePathEffect(3.0f,5.0f);
        effects[3] = new DashPathEffect(new float[]{20,10,5,10},phase);
        Path p = new Path();
        p.addRect(0,0,8,8,Path.Direction.CCW);
        effects[4] = new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);
        effects[5] = new ComposePathEffect(effects[2],effects[4]);
        effects[6] = new SumPathEffect(effects[4],effects[3]);
        canvas.translate(8,8);
        for (int i =0;i<=6;i++){
            paint.setPathEffect(effects[i]);
            paint.setColor(colors[i]);
            canvas.drawPath(path,paint);
            canvas.translate(0,60);
        }
        phase +=1;
        invalidate();
    }
}
