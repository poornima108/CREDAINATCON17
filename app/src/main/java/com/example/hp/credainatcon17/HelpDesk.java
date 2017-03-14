package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class HelpDesk extends AppCompatActivity {
    private String[] helpdesk;
    private int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Contact> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk);
        helpdesk = getResources().getStringArray(R.array.contacthelpdesk_submenu);
        int count = 0;
        for (String item : helpdesk) {
        Contact contact = new Contact(image_id[count], item);
        count++;
        list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_helpdesk);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ContactAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
        new HomeScreenRecyclerViewItemClickListener(HelpDesk.this, new HomeScreenRecyclerViewItemClickListener.OnItemClickListener() {
@Override
public void onItemClick(View view, int position) {
        if (position == 0) {
        Intent intent = new Intent(HelpDesk.this, CredaiNationHelpdesk.class);
        startActivity(intent);
        } else if (position == 1) {
        Intent intent = new Intent(HelpDesk.this, GettingArround.class);
        startActivity(intent);
        } else if (position == 2) {
        Intent intent = new Intent(HelpDesk.this, HotelHelpDesk.class);
        startActivity(intent);
        } else if (position == 3) {
        Intent intent = new Intent(HelpDesk.this, IndianHighCommision.class);
        startActivity(intent);
        }

        }
        })
        );

        }
        }
