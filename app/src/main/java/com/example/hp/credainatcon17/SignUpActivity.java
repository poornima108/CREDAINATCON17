package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity{
    private int day_x,month_x,year_x;
    static final int DIALOG = 0;
    private EditText companyName,name,phoneNumber,emailid,dob,passportNumber,passportExpiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        companyName = (EditText) findViewById(R.id.company_name);
        name = (EditText) findViewById(R.id.delegate_name);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        emailid = (EditText) findViewById(R.id.email_id_register);
        dob = (EditText) findViewById(R.id.dob_user);
        passportNumber = (EditText) findViewById(R.id.passport_number);
        passportExpiry = (EditText) findViewById(R.id.passport_expiry);
        calenderOnClick();

    }


    public void calenderOnClick(){
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);

        }
        return null;
    }
        private DatePickerDialog.OnDateSetListener datePickerListener =new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month_x = month;
                year_x = year;
                day_x = dayOfMonth;
                dob.setText(day_x + "/" + month_x + "/" + year_x);

            }
        };
    }


