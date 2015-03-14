package com.holyboom.flyer.mylaunchactivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by flyer on 15/3/12.
 */
public class ExpandableListActivityTest extends android.app.ExpandableListActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logos = new int[]{
                R.drawable.mail,
                R.drawable.map,
                R.drawable.folder
            };

            String[] thingTypes = new String[]{
              "mail","map","floder"
            };

            String[][] things = new String[][]{
                    {"QQ","Gmail","OutLook"},
                    {"Baidu","Google","Bing"},
                    {"apk","dpk","exe"}
            };

            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,64
                );
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return things[groupPosition][childPosition];
            }


            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return things[groupPosition].length;
            }
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }
            @Override
            public int getGroupCount() {
                return thingTypes.length;
            }
            @Override
            public Object getGroup(int groupPosition) {
                return thingTypes[groupPosition];
            }


            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }


            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListActivityTest.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(ExpandableListActivityTest.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }



            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
        setListAdapter(adapter);
    }
}
