package com.holyboom.flyer.listviewlikeinbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by flyer on 15/2/12.
 */
public abstract class BoomAdapter<T> extends BaseAdapter{
    private Context context;
    private List<T> content;
    private LayoutInflater layoutInflater;

    public abstract void setContent(ViewHolder viewHolder,int position);

    public abstract void setButtonClickListener(ViewHolder viewHolder,final int position);

    public abstract void bindViewHolder(ViewHolder viewHolder,View convertView);


    public void BoomAdapter(Context context,List<T> content){
        this.context = context;
        this.content = content;
        this.layoutInflater = LayoutInflater.from(context);
        //获取layoutInflater对象方便后getView使用
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public Object getItem(int position) {
        return content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;


        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list,parent,false);
            bindViewHolder(viewHolder,convertView);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            viewHolder.textContent.getLayoutParams().width = wm.getDefaultDisplay().getWidth();
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        setContent(viewHolder,position);
        convertView.setOnTouchListener(new ItemOnTouchListener(context,position));
        if (viewHolder.horizonTalScrollView.getScrollX() != 0)  {
            viewHolder.horizonTalScrollView.scrollTo(0, 0);
        }
        convertView.setOnClickListener(new ItemOnClickListener(context,position));
        return convertView;
    }



}
