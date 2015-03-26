package com.holyboom.flyer.pingpang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    int tableWidth;
    int tableHeight;
    int racketY;

    private final int RACKET_HEIGHT = 20;
    private final int RACKET_WIDTH = 70;

    private final int BALL_SIZE = 12;
    //小球纵向速度
    private int ySpeed = 10;
    Random random = new Random();

    private double xyRate = random.nextDouble() - 0.5;
    //小球横向速度
    private int xSpeed = (int) (ySpeed * xyRate *2);

    //小球位置
    private int ballX = random.nextInt(200)+20;
    private int ballY = random.nextInt(10)+20;
    //球拍水平位置
    int racketX = random.nextInt(200);

    private boolean isLose = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final GameView gameView = new GameView(MainActivity.this);
        setContentView(gameView);
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        tableWidth = displayMetrics.widthPixels;
        tableHeight = displayMetrics.heightPixels;

        racketY = tableHeight-80;
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123){
                    gameView.invalidate();
                }
            }
        };
        gameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_A:
                        if (racketX>0)
                            racketX -= 10;
                        break;
                    case KeyEvent.KEYCODE_D:
                        if (racketX<tableWidth -RACKET_WIDTH)
                            racketX +=10;
                        break;
                }
                gameView.invalidate();
                return true;
            }
        });

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ballX <= 0
                        || ballX >= tableWidth-BALL_SIZE){
                    xSpeed = -xSpeed;
                }
                if (ballY >= racketY-BALL_SIZE
                        &&(ballX < racketX
                        ||ballX >racketX +RACKET_WIDTH) ){
                    timer.cancel();
                    isLose = true;
                }else if (ballY <= 0
                        || (ballY >= racketY-BALL_SIZE
                        && ballX>racketX
                        &&ballX <= racketX+RACKET_WIDTH)){
                    ySpeed = -ySpeed;
                }
                ballY += ySpeed;
                ballX += xSpeed;
                handler.sendEmptyMessage(0x123);
            }
        },0,100);

    }


    class GameView extends View{
        Paint paint = new Paint();

        public GameView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            if (isLose){
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏结束",50,200,paint);
            }else {
                paint.setColor(Color.rgb(0,0,0));
                canvas.drawCircle(ballX,ballY,BALL_SIZE,paint);
                paint.setColor(Color.rgb(80,80,200));
                canvas.drawRect(racketX,racketY,racketX+RACKET_WIDTH,racketY+RACKET_HEIGHT,paint);
            }
        }
    }

}
