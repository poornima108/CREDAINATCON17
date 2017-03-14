package com.example.hp.credainatcon17;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by poornima 1 on 28-01-2017.
 */
public class SponserAdaptar extends RecyclerView.Adapter<SponserAdaptar.ContactViewHolder> {
    ArrayList<Sponser> contacts = new ArrayList<Sponser>();

    public SponserAdaptar(ArrayList<Sponser> contacts){
     this.contacts=contacts;
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponser_card_layout_view,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        Sponser con = contacts.get(position);
        holder.person_img.setImageResource(con.getImage_id());
        holder.person_name.setText(con.getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView person_img;
        TextView person_name;

        public ContactViewHolder(View view) {

            super(view);
            person_img = (ImageView) view.findViewById(R.id.person_image);
            person_name = (TextView) view.findViewById(R.id.person_name);


        }
    }
}
