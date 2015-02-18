package com.holyboom.flyer.listviewlikeinbox;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {



    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        listView.getViewTreeObserver();
        

    }
    class MyAdapter extends BoomAdapter{

        @Override
        public void setContent(ViewHolder viewHolder, int position) {

        }

        @Override
        public void setButtonClickListener(ViewHolder viewHolder, int position) {

        }

        @Override
        public void bindViewHolder(ViewHolder viewHolder, View convertView) {

        }
    }
}
