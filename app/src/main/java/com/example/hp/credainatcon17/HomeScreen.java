package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Grid> list = new ArrayList<Grid>();
    private int[] image_id = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        name = getResources().getStringArray(R.array.persons_name);
        int count = 0;
        for (String Name : name) {
            Grid contact = new Grid(image_id[count], Name);
            count++;
            list.add(contact);
        }
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users/" + userid);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_home_screen);
        LayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new GridAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new HomeScreenRecyclerViewItemClickListener(HomeScreen.this, new HomeScreenRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(HomeScreen.this, Info.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(HomeScreen.this, Natcon.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(HomeScreen.this, Schedules.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child("natconregistered").getValue().toString().equals("yes")) {
                                        Toast.makeText(HomeScreen.this, "ALREADY REGISTERED FOR NATCON17", Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(HomeScreen.this, NatconRegister.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else if (position == 4) {
                            Intent intent = new Intent(HomeScreen.this, Venue_travel.class);
                            startActivity(intent);
                        } else if (position == 5) {
                            Intent intent = new Intent(HomeScreen.this, Transport.class);
                            startActivity(intent);
                        } else if (position == 6) {
                            Intent intent = new Intent(HomeScreen.this, Networking.class);
                            startActivity(intent);
                        } else if (position == 7) {
                            Intent intent = new Intent(HomeScreen.this, Sponsers.class);
                            startActivity(intent);
                        } else if (position == 8) {
                            Intent intent = new Intent(HomeScreen.this, Contactus.class);
                            startActivity(intent);
                        }

                    }
                })
        );

    }
    boolean twice = false;

    @Override
    public void onBackPressed() {

        if (twice == true) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        twice = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);


        //  moveTaskToBack(true);
    }

}
