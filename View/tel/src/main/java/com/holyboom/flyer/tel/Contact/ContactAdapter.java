package com.holyboom.flyer.tel.Contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.holyboom.flyer.tel.R;

import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements View.OnClickListener{


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView numberView;
        public TextView locationView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name_view_contact);
            numberView = (TextView) itemView.findViewById(R.id.number_view_contact);
            locationView = (TextView) itemView.findViewById(R.id.location_view_contact);
        }
    }

    Context context;
    List<Contact> contactList;

    public ContactAdapter(Context context,List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
    }


    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_contact,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        holder.nameView.setText(contactList.get(position).contactName);
        holder.numberView.setText(contactList.get(position).contactNumber);
        holder.locationView.setText(contactList.get(position).contactLocation);
        holder.itemView.setTag(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onClick(View v) {

    }
}
