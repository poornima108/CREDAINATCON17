package com.example.hp.credainatcon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schedules extends BaseActivity {

    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_schedules, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        Toast toast = Toast.makeText(getApplicationContext(), "Scedules", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        expandableListView = (ExpandableListView)findViewById(R.id.expListView_Schedules);

        List<String> headings=new ArrayList<String>();
        List<String> L1=new ArrayList<String>();
        List<String> L2=new ArrayList<String>();
        List<String> L3=new ArrayList<String>();

        HashMap<String,List<String>> childlist=new HashMap<String, List<String>>();

        String heading_items[]=getResources().getStringArray(R.array.header_title);
        String l1[]=getResources().getStringArray(R.array.h1_items);
        String l2[]=getResources().getStringArray(R.array.h2_items);
        String l3[]=getResources().getStringArray(R.array.h3_items);

        for(String title: heading_items)
        {
            headings.add(title);
        }
        for(String title: l1)
        {
            L1.add(title);
        }
        for(String title: l2)
        {
            L2.add(title);
        }
        for(String title: l3)
        {
            L3.add(title);
        }

        childlist.put(headings.get(0),L1);
        childlist.put(headings.get(1),L2);
        childlist.put(headings.get(2),L3);

        ExpandableAdapter adapter=new ExpandableAdapter(this,headings,childlist);
        expandableListView.setAdapter(adapter);

    }
}
