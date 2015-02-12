package com.holyboom.flyer.listviewlikeinbox;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by flyer on 15/2/12.
 */
public class ItemOnTouchListener implements View.OnTouchListener{

    Context context;
    int position;
    ItemOnTouchListener(Context context, int position){
        this.context = context;
        this.position = position;
    }

    public boolean onTouch(View v, MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:

                //获得ViewHolder
                ViewHolder viewHolder = (ViewHolder) v.getTag();

                //获得HorizontalScrollView滑动的水平方向值.
                int scrollX = viewHolder.horizonTalScrollView.getScrollX();

                //获得操作区域的长度
//                int actionA = viewHolder.button.getWidth();
//                int actionB = viewHolder.buttonTest.getWidth();
//                int actionW = actionA+actionB;
                int actionW = viewHolder.action.getWidth();
                /**
                 *  注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                 ** 如果水平方向的移动值<操作区域的长度的一半,就复原
                 ** 否则的话显示操作区域
                 */
                if (scrollX < actionW / 3){
                    viewHolder.horizonTalScrollView.smoothScrollTo(0, 0);
                }else{
                    viewHolder.horizonTalScrollView.smoothScrollTo(actionW, 0);
                }
                return true;
        }
        return false;
    }
}
