package com.holyboom.flyer.animationandgraphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by flyer on 15/3/13.
 */
public class StudyBitmap extends ActionBarActivity{

    String[] images = null;
    AssetManager assets = null;
    int currentImgPosition = 0;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studybitmap_activity);
        image = (ImageView) findViewById(R.id.image_view);
        try {
            assets = getAssets();
            images = assets.list("");
        }catch (Exception e){
            e.printStackTrace();
        }
        final Button next = (Button) findViewById(R.id.next_image);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImgPosition >= images.length){
                    currentImgPosition = 0;
                }
                while (!images[currentImgPosition].endsWith(".png")
                    && !images[currentImgPosition].endsWith(".jpg")
                        && !images[currentImgPosition].endsWith(".gif")){
                    currentImgPosition++;
                    if (currentImgPosition >= images.length){
                        currentImgPosition = 0;
                    }
                }
                InputStream  assetFile = null;
                try {
                    assetFile = assets.open(images[currentImgPosition++]);
                }catch (Exception e){
                    e.printStackTrace();
                }
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
                if (bitmapDrawable != null
                        && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }

                image.setImageBitmap(BitmapFactory.decodeStream(assetFile));
                //从SD卡读取图片
                //image.setImageBitmap(BitmapFactory.decodeFile("/sdcard/Download/haizei.jpg"));
            }
        });
    }
}
