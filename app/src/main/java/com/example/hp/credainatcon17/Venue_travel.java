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

public class Venue_travel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Venue> list = new ArrayList<Venue>();
    private int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};
    private String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_travel);

        Toast toast = Toast.makeText(getApplicationContext(), "Venue and travel", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        name = getResources().getStringArray(R.array.venue_name);
        int count = 0;
        for (String Name : name) {
            Venue v = new Venue(image_id[count], Name);
            count++;
            list.add(v);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_venues);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new VenueAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new VenueRecyclerViewItemClickListener(Venue_travel.this, new VenueRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(Venue_travel.this, HotelInfo.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(Venue_travel.this, RoomInfo.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(Venue_travel.this, AirTickets.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(Venue_travel.this, VisaInfo.class);
                            startActivity(intent);
                        } else if (position == 4) {
                            Intent intent = new Intent(Venue_travel.this, Matrix.class);
                            startActivity(intent);
                        }

                    }
                })
        );


    }

}
