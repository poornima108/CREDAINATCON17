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
import android.util.Patterns;
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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import android.os.Handler;

public class NatconRegister extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RadioGroup rgCredaiMember;
    private Spinner spinnerChapterName;
    private EditText edtCompanyName, edtCompanyAddress1, edtCompanyAddress2, edtCity, edtPincode, edtDelegateName, edtDelegateNickName, edtDob, edtPhoneNumber, edtWhatsappNumber, edtEmailId;
    private Button btnNext, btnSkipRegistration;
    private String chapterName;
    private static View appView;
    private AwesomeValidation awesomeValidation;
    private Spinner spinnerState;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.edtNatcon_companyname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_companyaddress1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_companyaddress2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etdNatcon_delegatename, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etdNatcon_delegatenickname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edtdNatcon_phonenumber, "^[0-9]{10}$", R.string.phonenumbererror);
        awesomeValidation.addValidation(this, R.id.etdNatcon_whatsappnumber, "^[0-9]{10}$", R.string.phonenumbererror);
        awesomeValidation.addValidation(this, R.id.etdNatcon_emailid, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.etdNatcon_dob, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_city, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_pincode, "^[0-9]{6}$", R.string.pinerror);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations");

        rgCredaiMember = (RadioGroup) findViewById(R.id.rgNatcon_credaimember);
        spinnerChapterName = (Spinner) findViewById(R.id.spinnerNatcon_chaptername);
        spinnerState = (Spinner) findViewById(R.id.spinnerNatcon_state);
        edtCompanyName = (EditText) findViewById(R.id.edtNatcon_companyname);
        edtCompanyAddress1 = (EditText) findViewById(R.id.edtNatcon_companyaddress1);
        edtCompanyAddress2 = (EditText) findViewById(R.id.edtNatcon_companyaddress2);
        edtCity = (EditText) findViewById(R.id.edtNatcon_city);
        edtPincode = (EditText) findViewById(R.id.edtNatcon_pincode);
        edtDelegateName = (EditText) findViewById(R.id.etdNatcon_delegatename);
        edtDelegateNickName = (EditText) findViewById(R.id.etdNatcon_delegatenickname);
        edtDob = (EditText) findViewById(R.id.etdNatcon_dob);
        edtPhoneNumber = (EditText) findViewById(R.id.edtdNatcon_phonenumber);
        edtWhatsappNumber = (EditText) findViewById(R.id.etdNatcon_whatsappnumber);
        edtEmailId = (EditText) findViewById(R.id.etdNatcon_emailid);
        btnNext = (Button) findViewById(R.id.btnNatcon_next);
        btnSkipRegistration = (Button) findViewById(R.id.btnNatcon_skipregistration);
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
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state = adapterView.getItemAtPosition(i).toString();
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
        final String city = edtCity.getText().toString();
        final String phonenumber = edtPhoneNumber.getText().toString();
        final String dateofbirth = edtDob.getText().toString();
        final String pincode = edtPincode.getText().toString();
        final String whatsappnumber = edtWhatsappNumber.getText().toString();
        final String emailid = edtEmailId.getText().toString();


        if (awesomeValidation.validate()) {
            String[] age = edtDob.getText().toString().split("-");
            if (getAge(Integer.valueOf(age[2]), Integer.valueOf(age[1]), Integer.valueOf(age[0])) >= 18) {
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
            }else {
                Toast.makeText(this, "AGE SHOULD BE MINIMUM OF 18 YEARS", Toast.LENGTH_SHORT).show();
            }
        }
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

    public int getAge(int _year, int _month, int _day) {

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(java.util.Calendar.YEAR);
        m = cal.get(java.util.Calendar.MONTH);
        d = cal.get(java.util.Calendar.DAY_OF_MONTH);
        cal.set(_year, _month, _day);
        a = y - cal.get(java.util.Calendar.YEAR);
        if ((m < cal.get(java.util.Calendar.MONTH))
                || ((m == cal.get(java.util.Calendar.MONTH)) && (d < cal
                .get(java.util.Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        return a;
    }

}