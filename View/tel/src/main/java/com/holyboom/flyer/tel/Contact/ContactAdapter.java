package com.holyboom.flyer.tel.Contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ContactAdapter<T> extends RecyclerView.Adapter<ContactViewHolder> implements View.OnClickListener{

    private List<T> dataSet;
    private Context context;

    public ContactAdapter(Context context,List<T> dataSet){
        this.context = context;
        this.dataSet = dataSet;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }
}
