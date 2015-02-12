package com.holyboom.flyer.listviewlikeinbox;

import android.content.Context;
import android.view.View;

/**
 * Created by flyer on 15/2/12.
 */
public class ItemOnClickListener implements View.OnClickListener{
    int position;
    Context context;

    ItemOnClickListener(Context context,int position){
        this.position=position;
        this.context=context;
    }

    @Override
    public void onClick(View v) {

    }
}
