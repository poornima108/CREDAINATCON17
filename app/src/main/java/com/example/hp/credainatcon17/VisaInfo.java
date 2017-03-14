package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class VisaInfo extends AppCompatActivity {
    private String[] helpdesk;
    private int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Venue> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_info);
        helpdesk = getResources().getStringArray(R.array.helpdesk_submenu);
        int count = 0;
        for (String item : helpdesk) {
            Venue contact = new Venue(image_id[count], item);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_venue);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new VenueAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new HomeScreenRecyclerViewItemClickListener(VisaInfo.this, new HomeScreenRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(VisaInfo.this, CoveringLetters.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(VisaInfo.this, VisaRequirements.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(VisaInfo.this, CredaiLetter.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(VisaInfo.this, VisaFormDetail.class);
                            startActivity(intent);
                        }
                        else if (position == 4) {
                        Intent intent = new Intent(VisaInfo.this, SampleVisaForm.class);
                        startActivity(intent);
                    }

                    }
                })
        );

    }
}
