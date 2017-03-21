package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Contactus extends BaseActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Contact> list = new ArrayList<Contact>();
    private int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};
    private String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_contactus, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        Toast toast = Toast.makeText(getApplicationContext(), "contactus", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        name = getResources().getStringArray(R.array.contact_name);
        int count = 0;
        for (String Name : name) {
            Contact contact = new Contact(image_id[count], Name);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_contact_us);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ContactAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new ContactActivityRecyclerViewItemClickListener(Contactus.this, new ContactActivityRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(Contactus.this, CredaiDelhiOffice.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(Contactus.this, ContactUsOrganizingCommittee.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(Contactus.this, ContactUsCredaiChennai.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(Contactus.this, HelpDesk.class);
                            startActivity(intent);
                        } else if (position == 4) {
                            Intent intent = new Intent(Contactus.this, Emergency.class);
                            startActivity(intent);
                        }

                    }
                })
        );



    }
}
