package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private int day_x, month_x, year_x;
    static final int DIALOG = 0, Dialog = 1;
    private EditText companyName, name, phoneNumber, emailid, dob, passportNumber, passportExpiry;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final Calendar calendar = Calendar.getInstance();
        day_x = calendar.get(Calendar.DATE);
        month_x = calendar.get(Calendar.MONTH);
        year_x = calendar.get(Calendar.YEAR);
        companyName = (EditText) findViewById(R.id.company_name);
        name = (EditText) findViewById(R.id.delegate_name);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        emailid = (EditText) findViewById(R.id.email_id_register);
        dob = (EditText) findViewById(R.id.dob_user);
        passportNumber = (EditText) findViewById(R.id.passport_number);
        passportExpiry = (EditText) findViewById(R.id.passport_expiry);
        calenderOnClick();
        spinner = (Spinner) findViewById(R.id.spinner_chapter_name);
        adapter = ArrayAdapter.createFromResource(this, R.array.chapter_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void calenderOnClick() {
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);

            }
        });
        passportExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Dialog);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);

        }
        if (id == Dialog) {
            return new DatePickerDialog(this, expiryPickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month_x = month;
            year_x = year;
            day_x = dayOfMonth + 1;
            dob.setText(day_x + "/" + month_x + "/" + year_x);
        }
    };
    private DatePickerDialog.OnDateSetListener expiryPickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month_x = month;
            year_x = year;
            day_x = dayOfMonth + 1;
            passportExpiry.setText(day_x + "/" + month_x + "/" + year_x);
        }
    };
}


