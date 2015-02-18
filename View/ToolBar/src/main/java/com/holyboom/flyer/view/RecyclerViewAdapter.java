package com.holyboom.flyer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/2/18.
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<T> mDataset;
    private Context mContext;
    public RecyclerViewAdapter(List<T> mDataset,Context mContext){
        this.mDataset = mDataset;
        this.mContext = mContext;
        Log.e("RecyclerViewAdapter","构造方法");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("RecyclerViewAdapter","开始创建第"+i+"个view");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.e("RecyclerViewAdapter","完成第"+i+"个view的创建");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.e("RecyclerViewAdapter","开始绑定第"+i+"个viewHolder内容");
        viewHolder.mTextView.setText(i+"-fly");
        Log.e("RecyclerViewAdapter","完成绑定第"+i+"个viewHolder内容");
    }

    @Override
    public int getItemCount() {
        Log.e("RecyclerViewAdapter","一共有"+mDataset.size()+"个view");
        return mDataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
            //Log.e("RecyclerViewAdapter","构造方法");
        }
    }


}
