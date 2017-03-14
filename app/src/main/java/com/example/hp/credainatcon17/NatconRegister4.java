package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NatconRegister4 extends AppCompatActivity {
    private EditText edtNatcon4_delegatename, edtNatcon4_companyname, edtNatcon4_passportnumber, edtNatcon4_dob, edtNatcon4_emailid, edtNatcon4_contactnumber;
    private EditText edtNatcon4_childname, edtNatcon4_childpassportnumber, edtNatcon4_childdob;
    private Spinner spinnerNatcon4_chaptername;
    private CheckBox chkNatcon4_children;
    private Button btnNatcon4_next;
    private String chapterName;
    private RadioGroup rgNatcon4_mealpreference;
    private DatabaseReference databaseReference;
    private String userid;
    private static View appView;
    private DatabaseReference databaseReference1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register4);

        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations/" + userid);
        databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users/" + userid);
        edtNatcon4_delegatename = (EditText) findViewById(R.id.edtNatcon4_delegate_name);
        edtNatcon4_companyname = (EditText) findViewById(R.id.edtNatcon4_company_name);
        edtNatcon4_passportnumber = (EditText) findViewById(R.id.edtNatcon4_passport_number);
        edtNatcon4_dob = (EditText) findViewById(R.id.edtNatcon4_dob);
        edtNatcon4_emailid = (EditText) findViewById(R.id.edtNatcon4_email);
        edtNatcon4_contactnumber = (EditText) findViewById(R.id.edtNatcon4_contact_number);

        rgNatcon4_mealpreference = (RadioGroup) findViewById(R.id.rgNatcon4_mealspreference);

        spinnerNatcon4_chaptername = (Spinner) findViewById(R.id.spinnerNatcon4_chaptername);

        edtNatcon4_childname = (EditText) findViewById(R.id.edtNatcon4_child_name);
        edtNatcon4_childpassportnumber = (EditText) findViewById(R.id.edtNatcon4_child_passport_number);
        edtNatcon4_childdob = (EditText) findViewById(R.id.edtNatcon4_child_dob);

        chkNatcon4_children = (CheckBox) findViewById(R.id.chkNatcon4_children);

        btnNatcon4_next = (Button) findViewById(R.id.btnNatcon4_next4);

        List<String> categories = new ArrayList<>();
        categories.add("None");
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
        spinnerNatcon4_chaptername.setAdapter(dataAdapter);

        edtNatcon4_childdob.setVisibility(View.GONE);
        edtNatcon4_childname.setVisibility(View.GONE);
        edtNatcon4_childpassportnumber.setVisibility(View.GONE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        spinnerNatcon4_chaptername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chapterName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtNatcon4_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                NatconRegister4.DateDialog dialog = new NatconRegister4.DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });
        edtNatcon4_childdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                NatconRegister4.DateDialog dialog = new NatconRegister4.DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });

        chkNatcon4_children.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkNatcon4_children.isChecked()) {
                    edtNatcon4_childdob.setVisibility(View.VISIBLE);
                    edtNatcon4_childname.setVisibility(View.VISIBLE);
                    edtNatcon4_childpassportnumber.setVisibility(View.VISIBLE);
                } else {
                    edtNatcon4_childdob.setVisibility(View.GONE);
                    edtNatcon4_childname.setVisibility(View.GONE);
                    edtNatcon4_childpassportnumber.setVisibility(View.GONE);
                }
            }
        });

        btnNatcon4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtNatcon4_delegatename.getText().toString().isEmpty()) {
                    if (!edtNatcon4_companyname.getText().toString().isEmpty()) {
                        if (!edtNatcon4_passportnumber.getText().toString().isEmpty()) {
                            if (!edtNatcon4_dob.getText().toString().isEmpty()) {
                                if (!edtNatcon4_emailid.getText().toString().isEmpty() && isValidEmail(edtNatcon4_emailid.getText().toString())) {
                                    if (!edtNatcon4_contactnumber.getText().toString().isEmpty()) {
                                        databaseReference.child("twinspoucechapter").setValue(chapterName);
                                        databaseReference.child("twinspoucename").setValue(edtNatcon4_delegatename.getText().toString());
                                        databaseReference.child("twinspoucepassportnumber").setValue(edtNatcon4_passportnumber.getText().toString());
                                        databaseReference.child("twinspoucedob").setValue(edtNatcon4_dob.getText().toString());
                                        databaseReference.child("twinspouceemail").setValue(edtNatcon4_emailid.getText().toString());
                                        databaseReference.child("twinspoucephonenumber").setValue(edtNatcon4_contactnumber.getText().toString());
                                        databaseReference.child("twinspoucemealpreference").setValue(((RadioButton) findViewById(rgNatcon4_mealpreference.getCheckedRadioButtonId())).getText().toString());
                                        if (chkNatcon4_children.isChecked()) {
                                            if (!edtNatcon4_childname.getText().toString().isEmpty()) {
                                                if (!edtNatcon4_childpassportnumber.getText().toString().isEmpty()) {
                                                    if (!edtNatcon4_childdob.getText().toString().isEmpty()) {
                                                        databaseReference.child("children").setValue("yes");
                                                        databaseReference.child("childname").setValue(edtNatcon4_childname.getText().toString());
                                                        databaseReference.child("childpassportnumber").setValue(edtNatcon4_childpassportnumber.getText().toString());
                                                        databaseReference.child("childdob").setValue(edtNatcon4_childdob.getText().toString());
                                                        startActivity(new Intent(NatconRegister4.this, NatconRegistrationSummary.class));
                                                        finish();
                                                    } else {
                                                        Toast.makeText(NatconRegister4.this, "SELECT CHILD DATE OF BIRTH", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(NatconRegister4.this, "ENTER CHILD PASSPORT NUMBER", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(NatconRegister4.this, "ENTER CHILD NAME", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            databaseReference.child("children").setValue("no");
                                            databaseReference1.child("natconregistered").setValue("yes");
                                            startActivity(new Intent(NatconRegister4.this, NatconRegistrationSummary.class));
                                            finish();
                                        }

                                    } else {
                                        Toast.makeText(NatconRegister4.this, "ENTER CONTACT NUMBER", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(NatconRegister4.this, "ENTER EMAIL ID", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(NatconRegister4.this, "SELECT DATE OF BIRTH", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(NatconRegister4.this, "ENTER PASSPORT NUMBER", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(NatconRegister4.this, "ENTER COMPANY NAME", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NatconRegister4.this, "ENTER DELEGATE NAME", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
