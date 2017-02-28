package com.example.hp.credainatcon17;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Grid> list = new ArrayList<Grid>();
    private int[] image_id = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = getResources().getStringArray(R.array.persons_name);
        int count = 0;
        for (String Name : name) {
            Grid contact = new Grid(image_id[count], Name);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new GridAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new MainActivityRecyclerViewItemClickListener(MainActivity.this, new MainActivityRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this, Info.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(MainActivity.this, Natcon.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(MainActivity.this, Schedules.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(MainActivity.this, Registration.class);
                            startActivity(intent);
                        } else if (position == 4) {
                            Intent intent = new Intent(MainActivity.this, Venue_travel.class);
                            startActivity(intent);
                        }
                        else if (position == 5) {
                            Intent intent = new Intent(MainActivity.this, Transport.class);
                            startActivity(intent);
                        }
                        else if (position == 6) {
                            Intent intent = new Intent(MainActivity.this, Networking.class);
                            startActivity(intent);
                        }
                        else if (position == 7) {
                            Intent intent = new Intent(MainActivity.this, Sponsers.class);
                            startActivity(intent);
                        }
                        else if (position == 8) {
                            Intent intent = new Intent(MainActivity.this, Contactus.class);
                            startActivity(intent);
                        }

                    }
                })
        );

    }


}
