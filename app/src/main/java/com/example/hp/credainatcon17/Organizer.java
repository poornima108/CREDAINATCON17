package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Organizer extends AppCompatActivity {
    private String[] helpdesk;
    private int[] image_id = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.one};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Contact> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);
        helpdesk = getResources().getStringArray(R.array.helpdesk_submenu);
        int count = 0;
        for (String item : helpdesk) {
            Contact contact = new Contact(image_id[count], item);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_organizer);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ContactAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new HomeScreenRecyclerViewItemClickListener(Organizer.this, new HomeScreenRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(Organizer.this, CoveringLetters.class);
                            startActivity(intent);
                        }
                        if (position == 1) {
                            Intent intent = new Intent(Organizer.this, VisaRequirements.class);
                            startActivity(intent);
                        }
                        if (position == 2) {
                            Intent intent = new Intent(Organizer.this, CredaiLetter.class);
                            startActivity(intent);
                        }
                        if (position == 3) {
                            Intent intent = new Intent(Organizer.this, VisaFormDetail.class);
                            startActivity(intent);
                        }
                        if (position == 4) {
                            Intent intent = new Intent(Organizer.this, SampleVisaForm.class);
                            startActivity(intent);
                        }

                    }
                })
        );

    }
}
