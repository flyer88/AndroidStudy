package com.holyboom.flyer.valueanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class MainActivity extends ActionBarActivity {
    Animation animation;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_bar);
        //补间动画
        animation = AnimationUtils.loadAnimation(this,R.anim.button_anim);
        floatingActionButton.setAnimation(animation);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerAndZoonView(floatingActionButton);
            }
        });

    }

    private void centerAndZoonView (View view){
        RelativeLayout root = (RelativeLayout) findViewById( R.id.root );
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );

        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels/2 - (view.getMeasuredHeight()/2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, yDest - originalPos[1] );

        Animation scale
                = new ScaleAnimation(1.0f,root.getMeasuredWidth()/view.getMeasuredWidth() , 1.0f, root.getMeasuredHeight()/view.getMeasuredHeight(),
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new AccelerateInterpolator());

        AnimationSet set = new AnimationSet(false);

        set.addAnimation(scale);
        set.addAnimation(anim);

        set.setFillAfter(true);
        set.setDuration(100);
        //set.start();


        view.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){
            }

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                Intent intent = new Intent(getApplication(), TestActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "END OF ANIMATION", Toast.LENGTH_LONG).show();
            }
        });
    }
}
