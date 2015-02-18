package com.holyboom.flyer.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by flyer on 15/2/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    List<Actor> actorList;
    Context context;

    public MyAdapter(List<Actor> actorList, Context context){
        this.actorList = actorList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Actor p = actorList.get(i);
        viewHolder.textView.setText(p.name);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }
}
