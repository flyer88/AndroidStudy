package com.holyboom.flyer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by flyer on 15/2/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    String[] dataSet;
    Context context;

    public  MyAdapter(String[] dataSet,Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view_layout);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

       View v = LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.text_view,viewGroup,false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(dataSet[i]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
