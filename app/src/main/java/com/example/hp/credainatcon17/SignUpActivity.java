package com.example.hp.credainatcon17;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private int day_x, month_x, year_x;
    static final int DIALOG = 0;
    private EditText companyName, delegateName, phoneNumber, emailId, dob, passportNumber, passportExpiry;
    private Spinner spinner;
    DatabaseReference databaseReference;
    private String chapter;
    private Button signinBtn;
    private Button loginRedirect;
    private RadioGroup credaiMemberRadioGroup;
    private RadioGroup credaiYouthMemberRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credai-natcom-2017-fcba9.firebaseio.com/users");


        companyName = (EditText) findViewById(R.id.company_name);
        delegateName = (EditText) findViewById(R.id.delegate_name);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        emailId = (EditText) findViewById(R.id.email_id_register);
        dob = (EditText) findViewById(R.id.dob_user);
        passportNumber = (EditText) findViewById(R.id.passport_number);
        passportExpiry = (EditText) findViewById(R.id.passport_expiry);
        spinner = (Spinner) findViewById(R.id.spinner_chapter_name);
        credaiMemberRadioGroup = (RadioGroup) findViewById(R.id.credaimemberradiogroup);
        credaiYouthMemberRadioGroup = (RadioGroup) findViewById(R.id.credaiyouthradiogroup);

        signinBtn = (Button) findViewById(R.id.usersignup);
        loginRedirect = (Button) findViewById(R.id.userloginredirect);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Chapter 1");
        categories.add("Chapter 2");
        categories.add("Chapter 3");
        categories.add("Chapter 4");
        categories.add("Chapter 5");
        categories.add("Chapter 6");
        categories.add("Chapter 7");
        categories.add("Chapter 8");
        categories.add("Chapter 9");
        categories.add("Chapter 10");
        categories.add("Chapter 11");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }


    @Override
    protected void onStart() {
        super.onStart();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog dialog = new DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });
        passportExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog dialog = new DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chapter = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserDetails();
            }
        });
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void validateUserDetails() {


        String credaimember = ((RadioButton) findViewById(credaiMemberRadioGroup.getCheckedRadioButtonId())).getText().toString();
        String credaiyouthmember = ((RadioButton) findViewById(credaiYouthMemberRadioGroup.getCheckedRadioButtonId())).getText().toString();
        String delegatename = delegateName.getText().toString();
        String companyname = companyName.getText().toString();
        String credaichapter = chapter;
        String email = emailId.getText().toString();
        String phonenumber = phoneNumber.getText().toString();
        String dateofbirth = dob.getText().toString();
        String passportnumber = passportNumber.getText().toString();
        String passportexpiry = passportExpiry.getText().toString();
        if (!credaichapter.isEmpty()) {
            if (!delegatename.isEmpty()) {
                if (!companyname.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!phonenumber.isEmpty()) {
                            if (!dateofbirth.isEmpty()) {
                                if (!passportnumber.isEmpty()) {
                                    if (!passportexpiry.isEmpty()) {
                                        if (isValidEmail(email)) {
                                            DatabaseReference databaseReference1 = databaseReference.child(passportnumber);
                                            databaseReference1.child("name").setValue(delegatename);
                                            databaseReference1.child("email").setValue(email);
                                            databaseReference1.child("dob").setValue(dateofbirth);
                                            databaseReference1.child("companyname").setValue(companyname);
                                            databaseReference1.child("chapter").setValue(credaichapter);
                                            databaseReference1.child("phonenumber").setValue(phonenumber);
                                            databaseReference1.child("passportnumber").setValue(passportnumber);
                                            databaseReference1.child("passportexpiry").setValue(passportexpiry);
                                            databaseReference1.child("credaimember").setValue(credaimember);
                                            databaseReference1.child("credaiyouthmember").setValue(credaiyouthmember);
                                            databaseReference1.child("adminauth").setValue("pending");
                                            databaseReference1.child("paymentstatus").setValue("pending");
                                        }else {
                                            Toast.makeText(this, "PLEASE ENTER A VALID EMAIL ADDRESS", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(this, "PLEASE SELECT YOUR PASSPORT EXPIRY DATE", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "PLEASE ENTER YOUR PASSPORT NUMBER", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(this, "PLEASE SELECT YOUR DATE OF BIRTH", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "PLEASE ENTETR YOUR PHONE NUMBER", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "PLEASE ENTER YOUR EMAIL", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "PLEASE ENTER YOUR NAME", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "PLEASE ENTER YOUR COMPANY NAME", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "PLEASE SELECT A CHAPTER", Toast.LENGTH_SHORT).show();
        }

    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}


