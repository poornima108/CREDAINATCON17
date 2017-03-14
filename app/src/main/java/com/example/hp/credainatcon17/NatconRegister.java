package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import android.os.Handler;

public class NatconRegister extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RadioGroup rgCredaiMember;
    private Spinner spinnerChapterName;
    private EditText edtCompanyName, edtCompanyAddress1, edtCompanyAddress2, edtCity, edtState, edtPincode, edtDelegateName, edtDelegateNickName, edtDob, edtPhoneNumber, edtWhatsappNumber, edtEmailId;
    private Button btnNext, btnSkipRegistration;
    private String chapterName;
    private static View appView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations");
        rgCredaiMember = (RadioGroup) findViewById(R.id.rgNatcon_credaimember);
        spinnerChapterName = (Spinner) findViewById(R.id.spinnerNatcon_chaptername);
        edtCompanyName = (EditText) findViewById(R.id.edtNatcon_companyname);
        edtCompanyAddress1 = (EditText) findViewById(R.id.edtNatcon_companyaddress1);
        edtCompanyAddress2 = (EditText) findViewById(R.id.edtNatcon_companyaddress2);
        edtCity = (EditText) findViewById(R.id.edtNatcon_city);
        edtState = (EditText) findViewById(R.id.edtNatcon_state);
        edtPincode = (EditText) findViewById(R.id.edtNatcon_pincode);
        edtDelegateName = (EditText) findViewById(R.id.etdNatcon_delegatename);
        edtDelegateNickName = (EditText) findViewById(R.id.etdNatcon_delegatenickname);
        edtDob = (EditText) findViewById(R.id.etdNatcon_dob);
        edtPhoneNumber = (EditText) findViewById(R.id.edtdNatcon_phonenumber);
        edtWhatsappNumber = (EditText) findViewById(R.id.etdNatcon_whatsappnumber);
        edtEmailId = (EditText) findViewById(R.id.etdNatcon_emailid);
        btnNext = (Button) findViewById(R.id.btnNatcon_next);
        btnSkipRegistration = (Button) findViewById(R.id.btnNatcon_skipregistration);

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
        spinnerChapterName.setAdapter(dataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                NatconRegister.DateDialog dialog = new NatconRegister.DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });
        spinnerChapterName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chapterName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserDetails();
            }
        });
        btnSkipRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NatconRegister.this, HomeScreen.class));
                finish();
            }
        });
    }

    private void validateUserDetails() {


        final String credaimember = ((RadioButton) findViewById(rgCredaiMember.getCheckedRadioButtonId())).getText().toString();
        final String delegatename = edtDelegateName.getText().toString();
        final String delegatenickname = edtDelegateNickName.getText().toString();
        final String companyname = edtCompanyName.getText().toString();
        final String companyaddress1 = edtCompanyAddress1.getText().toString();
        final String companyaddress2 = edtCompanyAddress2.getText().toString();
        final String credaichapter = chapterName;
        final String city = edtCity.getText().toString();
        final String state = edtState.getText().toString();
        final String phonenumber = edtPhoneNumber.getText().toString();
        final String dateofbirth = edtDob.getText().toString();
        final String pincode = edtPincode.getText().toString();
        final String whatsappnumber = edtWhatsappNumber.getText().toString();
        final String emailid = edtEmailId.getText().toString();
        if (!companyname.isEmpty()) {
            if (!companyaddress1.isEmpty()) {
                if (!companyaddress2.isEmpty()) {
                    if (!city.isEmpty()) {
                        if (!state.isEmpty()) {
                            if (!pincode.isEmpty()) {
                                if (!delegatename.isEmpty()) {
                                    if (!delegatenickname.isEmpty()) {
                                        if (!dateofbirth.isEmpty()) {
                                            if (!emailid.isEmpty() && isValidEmail(emailid)) {
                                                if (!phonenumber.isEmpty()) {
                                                    if (!whatsappnumber.isEmpty()) {
                                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                        if (user == null) {
                                                            Toast.makeText(this, "FIREBASEAUTH USER=NULL", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            String userID = user.getUid().toString();
                                                            DatabaseReference databaseReference1 = databaseReference.child(userID);
                                                            databaseReference1.child("credaimember").setValue(credaimember);
                                                            databaseReference1.child("name").setValue(delegatename);
                                                            databaseReference1.child("nickname").setValue(delegatenickname);
                                                            databaseReference1.child("companyname").setValue(companyname);
                                                            databaseReference1.child("companyaddress").setValue(companyaddress1 + companyaddress2);
                                                            databaseReference1.child("chapter").setValue(chapterName);
                                                            databaseReference1.child("city").setValue(city);
                                                            databaseReference1.child("dob").setValue(dateofbirth);
                                                            databaseReference1.child("phonenumber").setValue(phonenumber);
                                                            databaseReference1.child("state").setValue(state);
                                                            databaseReference1.child("whatsappnumber").setValue(whatsappnumber);
                                                            databaseReference1.child("pincode").setValue(pincode);
                                                            databaseReference1.child("email").setValue(emailid);
                                                            Toast.makeText(NatconRegister.this, "REGISTRATION PART ONE SUCCESSFULL", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(NatconRegister.this, NatconRegister2.class));
                                                        }
                                                    } else {
                                                        Toast.makeText(this, "Please Enter Your Whatsapp Number", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(this, "Please Enter Valid Email-Id", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(this, "Please Select Your Date of Birth", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(this, "Please Enter Your Nick Name", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(this, "Please Enter Your Pincode", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Please Enter Your State", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Please Enter Your City", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please Enter Company Address", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please Enter Company Address", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Enter Company Name", Toast.LENGTH_SHORT).show();
        }


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