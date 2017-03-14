package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private EditText companyName, delegateName, phoneNumber, emailId, dob, passportNumber, passportExpiry,passWord;
    private Spinner spinner;
    private DatabaseReference databaseReference;
    private String chapter;
    private Button signinBtn;
    private Button loginRedirect;
    private RadioGroup credaiMemberRadioGroup;
    private RadioGroup credaiYouthMemberRadioGroup;
    private static View appView;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users");
        mAuth = FirebaseAuth.getInstance();

        companyName = (EditText) findViewById(R.id.edtSignup_companyname);
        delegateName = (EditText) findViewById(R.id.edtSignup_name);
        phoneNumber = (EditText) findViewById(R.id.edtSignup_phonenumber);
        emailId = (EditText) findViewById(R.id.edtSignup_email);
        passWord= (EditText) findViewById(R.id.edtSignup_password);
        dob = (EditText) findViewById(R.id.edtSignup_dob);
        passportNumber = (EditText) findViewById(R.id.edtSignup_passportnumber);
        passportExpiry = (EditText) findViewById(R.id.edtSignup_passportexpiry);
        spinner = (Spinner) findViewById(R.id.spinnerSignup_chaptername);
        credaiMemberRadioGroup = (RadioGroup) findViewById(R.id.rgSignup_member);
        credaiYouthMemberRadioGroup = (RadioGroup) findViewById(R.id.rgSignup_youthmember);

        signinBtn = (Button) findViewById(R.id.btnSignup_usersignup);
        loginRedirect = (Button) findViewById(R.id.btnSignup_userloginredirect);

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
        categories.add("Chapter 12");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


    @Override
    protected void onStart() {
        super.onStart();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                DateDialog dialog = new DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });
        passportExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final String credaimember = ((RadioButton) findViewById(credaiMemberRadioGroup.getCheckedRadioButtonId())).getText().toString();
        final String credaiyouthmember = ((RadioButton) findViewById(credaiYouthMemberRadioGroup.getCheckedRadioButtonId())).getText().toString();
        final String delegatename = delegateName.getText().toString();
        final String companyname = companyName.getText().toString();
        final String credaichapter = chapter;
        final String email = emailId.getText().toString();
        final String password=passWord.getText().toString();
        final String phonenumber = phoneNumber.getText().toString();
        final String dateofbirth = dob.getText().toString();
        final String passportnumber = passportNumber.getText().toString();
        final String passportexpiry = passportExpiry.getText().toString();

        if (!credaichapter.isEmpty()) {
            if (!delegatename.isEmpty()) {
                if (!companyname.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!phonenumber.isEmpty()) {
                            if (!dateofbirth.isEmpty()) {
                                if (!passportnumber.isEmpty()) {
                                    if (!passportexpiry.isEmpty()) {
                                        if (!password.isEmpty()) {
                                            if (isValidEmail(email)) {
                                                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            FirebaseUser user = task.getResult().getUser();
                                                            String userID = user.getUid().toString();
                                                            DatabaseReference databaseReference1 = databaseReference.child(userID);
                                                            databaseReference1.child("name").setValue(delegatename);
                                                            databaseReference1.child("email").setValue(email);
                                                            databaseReference1.child("password").setValue(password);
                                                            databaseReference1.child("dob").setValue(dateofbirth);
                                                            databaseReference1.child("companyname").setValue(companyname);
                                                            databaseReference1.child("chapter").setValue(credaichapter);
                                                            databaseReference1.child("phonenumber").setValue(phonenumber);
                                                            databaseReference1.child("passportnumber").setValue(passportnumber);
                                                            databaseReference1.child("passportexpiry").setValue(passportexpiry);
                                                            databaseReference1.child("credaimember").setValue(credaimember);
                                                            databaseReference1.child("credaiyouthmember").setValue(credaiyouthmember);
                                                            databaseReference1.child("adminauth").setValue("pending");
                                                            databaseReference1.child("natconregistered").setValue("no");
                                                            databaseReference1.child("paymentstatus").setValue("pending");
                                                            Toast.makeText(SignUpActivity.this, "Sign Up Successfull. Please Wait 2-3 Days For Activation Of Your Account.", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                                            finish();
                                                        } else {
                                                            Toast.makeText(SignUpActivity.this, "Not success", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                            } else {
                                                Toast.makeText(this, "PLEASE ENTER A VALID EMAIL ADDRESS", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(this, "PLEASE ENTER YOUR PASSWORD", Toast.LENGTH_SHORT).show();
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
        progressDialog.dismiss();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        EditText txtDate;
        private int day;
        private int month;
        private int year;

        public DateDialog() {
            txtDate = (EditText) appView;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DATE);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String date = day + "-" + (month + 1) + "-" + year;
            txtDate.setText(date);
        }
    }
}


