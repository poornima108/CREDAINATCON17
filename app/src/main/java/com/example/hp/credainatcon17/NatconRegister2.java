package com.example.hp.credainatcon17;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NatconRegister2 extends AppCompatActivity {
    private EditText edtPassportNumber, edtPassportExpiry, edtArrivalFrom, edtArrivalDate, edtArrivalFlightNumber, edtArrivalTime;
    private RadioGroup rgYouthWingMember, rgMealPreference;
    private Spinner spinnerUKVisaStatus;
    private Button btnNext2;
    private String UKVisaStatus;
    private static View appView;
    private DatabaseReference databaseReference;
    private CheckBox chkNeedAccomodation;
    private DatabaseReference databaseReference2;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natconregister2);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations");
        databaseReference2 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users");

        edtArrivalDate = (EditText) findViewById(R.id.edtNatcon_arrivaldate);
        edtArrivalFlightNumber = (EditText) findViewById(R.id.edtNatcon_arrivalflightnumber);
        edtArrivalFrom = (EditText) findViewById(R.id.edtNatcon_arrivalfrom);
        edtArrivalTime = (EditText) findViewById(R.id.edtNatcon_arrivaltime);
        edtPassportExpiry = (EditText) findViewById(R.id.edtNatcon_passportexpiry);
        edtPassportNumber = (EditText) findViewById(R.id.edtNatcon_passportnumber);
        rgMealPreference = (RadioGroup) findViewById(R.id.rgNatcon_mealspreference);
        rgYouthWingMember = (RadioGroup) findViewById(R.id.rgNatcon_credaiyouthmember);
        spinnerUKVisaStatus = (Spinner) findViewById(R.id.spinnerNatcom_ukvisastatus);
        btnNext2 = (Button) findViewById(R.id.btnNatcon_next2);
        chkNeedAccomodation = (CheckBox) findViewById(R.id.chkNatcon_needaccomodation);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.edtNatcon_passportnumber, "^[A-Za-z]{1}[0-9]{2}\\s?[0-9]{5}$", R.string.passporterror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_passportexpiry, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_arrivaldate, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_arrivalfrom, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        //awesomeValidation.addValidation(this, R.id.edtNatcon_arrivalflightnumber, "^([a-z][a-z]|[a-z][0-9]|[0-9][a-z])[a-z]?[0-9]{1,4}[a-z]?$", R.string.flightnumbererror);
        awesomeValidation.addValidation(this, R.id.edtNatcon_arrivaltime, "^([01]?[0-9]|2[0-3]):([0-9]|[0-5][0-9])$", R.string.arrivaltimeerror);


    }

    @Override
    protected void onStart() {
        super.onStart();
        spinnerUKVisaStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UKVisaStatus = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtPassportExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                NatconRegister2.DateDialog dialog = new NatconRegister2.DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });

        edtArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                NatconRegister2.DateDialog dialog = new NatconRegister2.DateDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Date Picker");
            }
        });

        edtArrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appView = view;
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserDetails();
            }
        });

    }

    private void validateUserDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = null, d2 = null, d3 = null, d4 = null, sysDate = null;
        String[] pe = new String[0];
        String[] ad = new String[0];
        if (awesomeValidation.validate()) {
            try {
                pe = edtPassportExpiry.getText().toString().split("-");
                ad = edtArrivalDate.getText().toString().split("-");

                d3 = sdf.parse(edtArrivalDate.getText().toString());
                sysDate = sdf.parse(sdf.format(new Date()));
                d4 = sdf.parse("13-08-2017");
            } catch (ParseException e) {
                Log.e("DATE ERROR", "validateUserDetails: "+e );
            }
            if (!(Integer.valueOf(pe[1])<12 && Integer.valueOf(pe[2])<=2017)) {
                    if (d3.compareTo(sysDate) > 0) {
                    if (!(d3.compareTo(d4) > 0)){
                        final String passportnumber = edtPassportNumber.getText().toString();
                        final String passportexpiry = edtPassportExpiry.getText().toString();
                        final String credaiyouthmember = ((RadioButton) findViewById(rgYouthWingMember.getCheckedRadioButtonId())).getText().toString();
                        final String visastatus = UKVisaStatus;
                        final String mealpreference = ((RadioButton) findViewById(rgMealPreference.getCheckedRadioButtonId())).getText().toString();
                        final String arrivalfrom = edtArrivalFrom.getText().toString();
                        final String arrivaldate = edtArrivalDate.getText().toString();
                        final String arrivalflightnumber = edtArrivalFlightNumber.getText().toString();
                        final String arrivaltime = edtArrivalTime.getText().toString();

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user == null) {
                            Toast.makeText(this, "FIREBASEAUTH USER=NULL", Toast.LENGTH_SHORT).show();
                        } else {
                            String userID = user.getUid().toString();
                            DatabaseReference databaseReference1 = databaseReference.child(userID);
                            databaseReference1.child("credaiyouthmember").setValue(credaiyouthmember);
                            databaseReference1.child("passportnumber").setValue(passportnumber);
                            databaseReference1.child("passportexpiry").setValue(passportexpiry);
                            databaseReference1.child("visastatus").setValue(visastatus);
                            databaseReference1.child("mealpreference").setValue(mealpreference);
                            databaseReference1.child("arrivalfrom").setValue(arrivalfrom);
                            databaseReference1.child("arrivaldate").setValue(arrivaldate);
                            databaseReference1.child("arrivalflightnumber").setValue(arrivalflightnumber);
                            databaseReference1.child("arrivaltime").setValue(arrivaltime);
                            if (chkNeedAccomodation.isChecked()) {
                                startActivity(new Intent(NatconRegister2.this, NatconRegister3.class));
                                Toast.makeText(NatconRegister2.this, "REGISTRATION PART TWO SUCCESSFULL", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference1.child("typeofaccomodation").setValue("notneeded");
                                databaseReference2.child(userID).child("natconregistered").setValue("yes");
                                Toast.makeText(NatconRegister2.this, "REGISTRATION SUCCESSFULL", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NatconRegister2.this, NatconRegistrationSummary.class));
                            }
                        }
                    }else {
                        Toast.makeText(this, "ARRIVAL DATE SHOULD BE BETWWEN NATCON EVENT", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "ARRIVAL DATE CAN NOT BE IN THE PAST", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "PASSPORT EXPIRY DATE SHOULD BE AFTER DECEMBER 2017", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        EditText time;
        private int hour;
        private int minute;

        public TimePickerFragment() {
            time = (EditText) appView;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
            time.setText(hourOfDay + ":" + minute1);
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
}
