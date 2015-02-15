package com.holyboom.flyer.fragmentpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by flyer on 15/2/15.
 */
public class NewsAdapter extends ArrayAdapter<News>{

    int resourceId;
    LayoutInflater layoutInflater ;
    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context,textViewResourceId, objects);
        this.resourceId = textViewResourceId;
        layoutInflater = LayoutInflater.from(getContext());
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        News news = getItem(position);
        View view;
        if (convertView == null){
            view = layoutInflater.inflate(resourceId,null);
        }else {
            view = convertView;
        }
        TextView newsTitleText = (TextView)view.findViewById(R.id.news_title);
        newsTitleText.setText(news.getTitle());
        return view;
    }


}
