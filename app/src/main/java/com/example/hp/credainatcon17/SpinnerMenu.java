package com.example.hp.credainatcon17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 20-02-2017.
 */


public class SpinnerMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSignup_chaptername);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Chapter 1");
        categories.add("Chapter 2");
        categories.add("Chapter 3");
        categories.add("Chapter 4");
        categories.add("Chapter 5");
        categories.add("Chapter 7");
        categories.add("Chapter 8");
        categories.add("Chapter 9");
        categories.add("Chapter 10");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
