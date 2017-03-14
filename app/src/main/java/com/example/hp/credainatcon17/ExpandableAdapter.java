package com.example.hp.credainatcon17;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Windows on 24-02-2017.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<String> header_titles;
    private HashMap<String,List<String>> child_titles;
    private Context context;

    ExpandableAdapter(Context context, List<String> header_titles, HashMap<String,List<String>> child_titles)
    {
        this.context=context;
        this.child_titles=child_titles;
        this.header_titles=header_titles;
    }

    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupposition) {
        return child_titles.get(header_titles.get(groupposition)).size();
    }

    @Override
    public Object getGroup(int groupposition) {
        return header_titles.get(groupposition);
    }

    @Override
    public Object getChild(int groupposition, int childposition) {
        return child_titles.get( header_titles.get(groupposition)).get(childposition);
    }

    @Override
    public long getGroupId(int groupposition) {
        return groupposition;
    }

    @Override
    public long getChildId(int groupposition, int childposition) {
        return childposition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupposition, boolean b, View view, ViewGroup viewGroup) {

       String title=(String) this.getGroup(groupposition);
        if(view== null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.parent_layout,null);
        }
        TextView textView= (TextView) view.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);

        return view;
    }

    @Override
    public View getChildView(int groupposition, int childposition, boolean b, View view, ViewGroup viewGroup) {
        String title=(String) this.getChild(groupposition,childposition);
        if(view== null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.child_layout,null);
        }
        TextView textView= (TextView) view.findViewById(R.id.child_item);
        textView.setText(title);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupposition, int childposition) {
        return false;
    }
}
