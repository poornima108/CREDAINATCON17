package com.example.hp.credainatcon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

public class Sponsers extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Sponser> list = new ArrayList<Sponser>();
    private int[] image_id = {R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsers);

        Toast toast = Toast.makeText(getApplicationContext(), "sponsers", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        name = getResources().getStringArray(R.array.sponsers_name);
        int count = 0;
        for (String Name : name) {
            Sponser contact = new Sponser(image_id[count], Name);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_sponser);
        LayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new SponserAdaptar(list);
        recyclerView.setAdapter(adapter);
    }

}
