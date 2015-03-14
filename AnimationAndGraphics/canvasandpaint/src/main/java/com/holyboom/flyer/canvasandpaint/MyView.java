package com.holyboom.flyer.canvasandpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by flyer on 15/3/13.
 */
public class MyView extends View{
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //画圆
        canvas.drawCircle(40,40,30,paint);
        //画矩形
        canvas.drawRect(10,80,70,140,paint);
        //画矩形
        canvas.drawRect(10,150,70,190,paint);

        RectF rectF = new RectF(10,200,70,230);
        //画圆角矩形
        canvas.drawRoundRect(rectF,15,15,paint);

        RectF rectF1 = new RectF(10,240,70,270);
        //画椭圆
        canvas.drawOval(rectF1,paint);

        //画三角形
        Path path = new Path();
        path.moveTo(10,340);
        path.lineTo(70,340);
        path.lineTo(40,290);
        path.close();
        canvas.drawPath(path,paint);
        //画五角形
        Path path1 = new Path();
        path1.moveTo(26,360);
        path1.lineTo(54,360);
        path1.lineTo(70,392);
        path1.lineTo(40,420);
        path1.lineTo(10,392);
        path1.close();
        canvas.drawPath(path1,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(120,40,30,paint);
        canvas.drawRect(90,80,150,140,paint);
        Shader shader = new LinearGradient(0,0,40,60,new
                int[] {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},
                null,Shader.TileMode.REPEAT);
        paint.setShader(shader);
        paint.setShadowLayer(45,10,10,Color.GRAY);
        canvas.drawCircle(200,40,30,paint);
        paint.setTextSize(24);
        paint.setShader(null);
        canvas.drawText("草你爸爸",240,50,paint);
    }
}
