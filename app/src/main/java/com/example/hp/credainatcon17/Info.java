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

public class Info extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ArrayList<Contact> list = new ArrayList<>();
    private int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toast toast = Toast.makeText(getApplicationContext(), "Info", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        name = getResources().getStringArray(R.array.info_name);
        int count = 0;
        for (String Name : name) {
            Contact contact = new Contact(image_id[count], Name);
            count++;
            list.add(contact);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_info);
        LayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ContactAdaptar(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new InfoActivityRecyclerViewItemClickListener(Info.this, new InfoActivityRecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            Intent intent = new Intent(Info.this, Credai1.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(Info.this, Organizer.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(Info.this, OrganizingCommittee.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(Info.this, AboutNatcon.class);
                            startActivity(intent);
                        } else if (position == 4) {
                            Intent intent = new Intent(Info.this, AboutLondon.class);
                            startActivity(intent);
                        }

                    }
                })
        );

    }

}
