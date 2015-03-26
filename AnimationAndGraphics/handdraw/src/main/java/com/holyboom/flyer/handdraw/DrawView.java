package com.holyboom.flyer.handdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by flyer on 15/3/14.
 */
public class DrawView extends View{

    float preX,preY;
    Path path;
    Paint paint = null;
    WindowManager windowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
    final int VIEW_WIDTH = windowManager.getDefaultDisplay().getWidth();
    final int VIEW_HEIGHT= windowManager.getDefaultDisplay().getHeight();
    Bitmap cacheBitmap = null;
    Canvas cacheCanvas = null;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();
        cacheCanvas.setBitmap(cacheBitmap);
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX = x;
                preY = y;
                break;
            //判断手离开屏幕，结束当前path，将其绘画到cacheCanvas上
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint bmpPaint = new Paint();
        //先将保存的bitmap绘画
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        //根据path的改变，在cacheBitmap上画上更新的path
        canvas.drawPath(path,paint);
    }
}
