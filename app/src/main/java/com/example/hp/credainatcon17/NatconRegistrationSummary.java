package com.example.hp.credainatcon17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NatconRegistrationSummary extends AppCompatActivity {

    EditText tx10dinner;
    EditText txarrivalflightnumber;
    EditText txarrivalfrom;
    EditText txarrivaltime;
    EditText txchapter;
    EditText txcity;
    EditText txcompanyaddress;
    EditText txcompanyname;
    EditText txdob;
    EditText txemail;
    EditText txgalanight;
    EditText txmealpreference;
    EditText txname;
    EditText txnickname;
    EditText txpassportexpiry;
    EditText txpassportnumber;
    EditText txphonenumber;
    EditText txpincode;
    EditText txroomtype;
    EditText txroomtypeperextranight;
    EditText txstate;
    EditText txtypeofaccomodation;
    EditText txvisastatus;
    EditText txwhatsappnumber;
    Button btnext;
    EditText txarrivaldate;
    EditText txcredaimember;
    EditText txchildren;
    EditText txtwinspoucechapter;
    EditText txtwinspoucedob;
    EditText txtwinspouceeamil;
    EditText txtwinspoucemealpreference;
    EditText txtwinspoucename;
    EditText txtwinspoucepassportnumber;
    EditText txtwinspoucephonenumber;
    private DatabaseReference databaseReference;
    Button btnEditButton;
    private DatabaseReference mRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_registration_summary);

        btnEditButton = (Button) findViewById(R.id.btnsummarypage_editbutton);
        tx10dinner = (EditText) findViewById(R.id.edtsummarypage10dinner);
        txarrivalflightnumber = (EditText) findViewById(R.id.edtsummarypageflightnumber);
        txarrivalfrom = (EditText) findViewById(R.id.edtsummarypagearrivalfrom);
        txarrivaltime = (EditText) findViewById(R.id.edtsummarypagearrivaltime);
        txchapter = (EditText) findViewById(R.id.edtsummarypagechapter);
        txcity = (EditText) findViewById(R.id.edtsummarypage_city);
        txcompanyaddress = (EditText) findViewById(R.id.edtsummarypage_companyaddress);
        txcompanyname = (EditText) findViewById(R.id.etdsummarypage_companyname);
        txdob = (EditText) findViewById(R.id.etdsummarypage_dob);
        txemail = (EditText) findViewById(R.id.edtsummarypage_emailid);
        txgalanight = (EditText) findViewById(R.id.edtsummarypage_galanight);
        txmealpreference = (EditText) findViewById(R.id.edtsummarypage_mealpreference);
        txname = (EditText) findViewById(R.id.edtsummarypage_name);
        txnickname = (EditText) findViewById(R.id.edtsummarypage_nickname);
        txpassportexpiry = (EditText) findViewById(R.id.edtsummarypage_passportexpiry);
        txpassportnumber = (EditText) findViewById(R.id.edtsummarypage_passportnumber);
        txphonenumber = (EditText) findViewById(R.id.edtsummarypage_phonenumber);
        txpincode = (EditText) findViewById(R.id.edtsummarypage_pincode);
        txroomtype = (EditText) findViewById(R.id.edtsummarypage_roomtype);
        txroomtypeperextranight = (EditText) findViewById(R.id.edtsummarypage_roomtypeextranight);
        txstate = (EditText) findViewById(R.id.edtsummarypage_state);
        txtypeofaccomodation = (EditText) findViewById(R.id.edtsummarypage_typeofaccomodation);
        txvisastatus = (EditText) findViewById(R.id.edtsummarypage_visastatus);
        txwhatsappnumber = (EditText) findViewById(R.id.edtsummarypage_whatsappnumber);
        txcredaimember = (EditText) findViewById(R.id.edtsummarypage_credaimember);
        txarrivaldate = (EditText) findViewById(R.id.edtsummarypage_arrivaldate);
        txchildren = (EditText) findViewById(R.id.edtsummarypage_children);
        txtwinspoucechapter = (EditText) findViewById(R.id.edtsummarypage_twinspoucechapter);
        txtwinspoucedob = (EditText) findViewById(R.id.edtsummarypage_twinspoucedob);
        txtwinspouceeamil = (EditText) findViewById(R.id.edtsummarypage_twinspouceemail);
        txtwinspoucemealpreference = (EditText) findViewById(R.id.edtsummarypage_twinspoucemealpreference);
        txtwinspoucename = (EditText) findViewById(R.id.edtsummarypage_twinspoucename);
        txtwinspoucepassportnumber = (EditText) findViewById(R.id.edtsummarypage_twinspoucepassportnumber);
        txtwinspoucephonenumber = (EditText) findViewById(R.id.edtsummarypage_twinspoucephonenumber);

        tx10dinner.setFocusableInTouchMode(false);
        txarrivalflightnumber.setFocusableInTouchMode(false);
        txarrivalfrom.setFocusableInTouchMode(false);
        txarrivaltime.setFocusableInTouchMode(false);
        txchapter.setFocusableInTouchMode(false);
        txcity.setFocusableInTouchMode(false);
        txcompanyaddress.setFocusableInTouchMode(false);
        txcompanyname.setFocusableInTouchMode(false);
        txdob.setFocusableInTouchMode(false);
        txemail.setFocusableInTouchMode(false);
        txgalanight.setFocusableInTouchMode(false);
        txmealpreference.setFocusableInTouchMode(false);
        txname.setFocusableInTouchMode(false);
        txnickname.setFocusableInTouchMode(false);
        txpassportexpiry.setFocusableInTouchMode(false);
        txpassportnumber.setFocusableInTouchMode(false);
        txphonenumber.setFocusableInTouchMode(false);
        txpincode.setFocusableInTouchMode(false);
        txroomtype.setFocusableInTouchMode(false);
        txroomtypeperextranight.setFocusableInTouchMode(false);
        txstate.setFocusableInTouchMode(false);
        txtypeofaccomodation.setFocusableInTouchMode(false);
        txvisastatus.setFocusableInTouchMode(false);
        txwhatsappnumber.setFocusableInTouchMode(false);
        txcredaimember.setFocusableInTouchMode(false);
        txarrivaldate.setFocusableInTouchMode(false);
        txchildren.setFocusableInTouchMode(false);
        txtwinspoucechapter.setFocusableInTouchMode(false);
        txtwinspoucedob.setFocusableInTouchMode(false);
        txtwinspouceeamil.setFocusableInTouchMode(false);
        txtwinspoucemealpreference.setFocusableInTouchMode(false);
        txtwinspoucename.setFocusableInTouchMode(false);
        txtwinspoucepassportnumber.setFocusableInTouchMode(false);
        txtwinspoucephonenumber.setFocusableInTouchMode(false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid().toString();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations/" + userID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog = new ProgressDialog(NatconRegistrationSummary.this);
                progressDialog.setMessage("Loading Please Wait");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                String value = (String) dataSnapshot.child("10thdinner").getValue();
                String value2 = (String) dataSnapshot.child("arrivalflightnumber").getValue();
                String value3 = (String) dataSnapshot.child("arrivalfrom").getValue();
                String value4 = (String) dataSnapshot.child("arrivaltime").getValue();
                String value5 = (String) dataSnapshot.child("chapter").getValue();
                String value6 = (String) dataSnapshot.child("city").getValue();
                String value7 = (String) dataSnapshot.child("companyaddress").getValue();
                String value8 = (String) dataSnapshot.child("companyname").getValue();
                String value9 = (String) dataSnapshot.child("dob").getValue();
                String value10 = (String) dataSnapshot.child("email").getValue();
                String value11 = (String) dataSnapshot.child("galanight").getValue();
                String value12 = (String) dataSnapshot.child("mealpreference").getValue();
                String value13 = (String) dataSnapshot.child("name").getValue();
                String value14 = (String) dataSnapshot.child("nickname").getValue();
                String value15 = (String) dataSnapshot.child("passportexpiry").getValue();
                String value16 = (String) dataSnapshot.child("passportnumber").getValue();
                String value17 = (String) dataSnapshot.child("phonenumber").getValue();
                String value18 = (String) dataSnapshot.child("pincode").getValue();
                String value19 = (String) dataSnapshot.child("roomtype").getValue();
                String value20 = (String) dataSnapshot.child("roomtypeextranight").getValue();
                String value21 = (String) dataSnapshot.child("state").getValue();
                String value22 = (String) dataSnapshot.child("typeofaccomodation").getValue();
                String value23 = (String) dataSnapshot.child("visastatus").getValue();
                String value24 = (String) dataSnapshot.child("whatsappnumber").getValue();
                String value25 = (String) dataSnapshot.child("credaimember").getValue();
                String value26 = (String) dataSnapshot.child("arrivaldate").getValue();
                String value27 = (String) dataSnapshot.child("children").getValue();
                String value28 = (String) dataSnapshot.child("twinspoucechapter").getValue();
                String value29 = (String) dataSnapshot.child("twinspoucedob").getValue();
                String value30 = (String) dataSnapshot.child("twinspouceemail").getValue();
                String value31 = (String) dataSnapshot.child("twinspoucemealpreference").getValue();
                String value32 = (String) dataSnapshot.child("twinspoucename").getValue();
                String value33 = (String) dataSnapshot.child("twinspoucepassportnumber").getValue();
                String value34 = (String) dataSnapshot.child("twinspoucephonenumber").getValue();

                tx10dinner.setText(value);
                txarrivalflightnumber.setText(value2);
                txarrivalfrom.setText(value3);
                txarrivaltime.setText(value4);
                txchapter.setText(value5);
                txcity.setText(value6);
                txcompanyaddress.setText(value7);
                txcompanyname.setText(value8);
                txdob.setText(value9);
                txemail.setText(value10);
                txgalanight.setText(value11);
                txmealpreference.setText(value12);
                txname.setText(value13);
                txnickname.setText(value14);
                txpassportexpiry.setText(value15);
                txpassportnumber.setText(value16);
                txphonenumber.setText(value17);
                txpincode.setText(value18);
                txroomtype.setText(value19);
                txroomtypeperextranight.setText(value20);
                txstate.setText(value21);
                txtypeofaccomodation.setText(value22);
                txvisastatus.setText(value23);
                txwhatsappnumber.setText(value24);
                txcredaimember.setText(value25);
                txarrivaldate.setText(value26);
                txchildren.setText(value27);
                txtwinspoucechapter.setText(value28);
                txtwinspoucedob.setText(value29);
                txtwinspouceeamil.setText(value30);
                txtwinspoucemealpreference.setText(value31);
                txtwinspoucename.setText(value32);
                txtwinspoucepassportnumber.setText(value33);
                txtwinspoucephonenumber.setText(value34);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void summaryupdate(View view) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final String tendinner = tx10dinner.getText().toString();
        databaseReference.child("10thdinner").setValue(tendinner);
        final String arrivalflight = txarrivalflightnumber.getText().toString();
        databaseReference.child("arrivalflightnumber").setValue(arrivalflight);
        final String arrivalfrom = txarrivalfrom.getText().toString();
        databaseReference.child("arrivalfrom").setValue(arrivalfrom);
        final String arrivaltime = txarrivaltime.getText().toString();
        databaseReference.child("arrivaltime").setValue(arrivaltime);
        final String chapter = txchapter.getText().toString();
        databaseReference.child("chapter").setValue(chapter);
        final String city = txcity.getText().toString();
        databaseReference.child("city").setValue(city);
        final String companyadd = txcompanyaddress.getText().toString();
        databaseReference.child("companyaddress").setValue(companyadd);
        final String companyname = txcompanyname.getText().toString();
        databaseReference.child("companyname").setValue(companyname);
        final String dob = txdob.getText().toString();
        databaseReference.child("dob").setValue(dob);
        final String email = txemail.getText().toString();
        databaseReference.child("email").setValue(email);
        final String galanight = txgalanight.getText().toString();
        databaseReference.child("galanight").setValue(galanight);
        final String mealpreference = txmealpreference.getText().toString();
        databaseReference.child("mealpreference").setValue(mealpreference);
        final String name = txname.getText().toString();
        databaseReference.child("name").setValue(name);
        final String nickname = txnickname.getText().toString();
        databaseReference.child("nickname").setValue(nickname);
        final String passportexpiry = txpassportexpiry.getText().toString();
        databaseReference.child("passportexpiry").setValue(passportexpiry);
        final String passnum = txpassportnumber.getText().toString();
        databaseReference.child("passportnumber").setValue(passnum);
        final String phonenum = txphonenumber.getText().toString();
        databaseReference.child("phonenumber").setValue(phonenum);
        final String pincode = txpincode.getText().toString();
        databaseReference.child("pincode").setValue(pincode);
        final String roomtype = txroomtype.getText().toString();
        databaseReference.child("roomtype").setValue(roomtype);
        final String roomextra = txroomtypeperextranight.getText().toString();
        databaseReference.child("roomtypeextranight").setValue(roomextra);
        final String state = txstate.getText().toString();
        databaseReference.child("state").setValue(state);
        final String acctype = txtypeofaccomodation.getText().toString();
        databaseReference.child("typeofaccomodation").setValue(acctype);
        final String visasta = txvisastatus.getText().toString();
        databaseReference.child("visastatus").setValue(visasta);
        final String whatnum = txwhatsappnumber.getText().toString();
        databaseReference.child("whatsappnumber").setValue(whatnum);
        final String credaimember = txcredaimember.getText().toString();
        databaseReference.child("credaimember").setValue(credaimember);
        final String arrivaldate = txarrivaldate.getText().toString();
        databaseReference.child("arrivaldate").setValue(arrivaldate);

        databaseReference.child("children").setValue(txchildren.getText().toString());
        databaseReference.child("twinspoucechapter").setValue(txtwinspoucechapter.getText().toString());
        databaseReference.child("twinspoucedob").setValue(txtwinspoucedob.getText().toString());
        databaseReference.child("twinspouceemail").setValue(txtwinspouceeamil.getText().toString());
        databaseReference.child("twinspoucemealpreference").setValue(txtwinspoucemealpreference.getText().toString());
        databaseReference.child("twinspoucename").setValue(txtwinspoucename.getText().toString());
        databaseReference.child("twinspoucepassportnumber").setValue(txtwinspoucepassportnumber.getText().toString());
        databaseReference.child("twinspoucephonenumber").setValue(txtwinspoucephonenumber.getText().toString());

        Toast.makeText(this, "THANK YOU FOR REGISTRING FOR NATCON17", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
        startActivity(new Intent(NatconRegistrationSummary.this, HomeScreen.class));
        finish();

    }

    public void editpage(View view) {

        tx10dinner.setFocusableInTouchMode(true);
        txarrivalflightnumber.setFocusableInTouchMode(true);
        txarrivalfrom.setFocusableInTouchMode(true);
        txarrivaltime.setFocusableInTouchMode(true);
        txchapter.setFocusableInTouchMode(true);
        txcity.setFocusableInTouchMode(true);
        txcompanyaddress.setFocusableInTouchMode(true);
        txcompanyname.setFocusableInTouchMode(true);
        txdob.setFocusableInTouchMode(true);
        txemail.setFocusableInTouchMode(true);
        txgalanight.setFocusableInTouchMode(true);
        txmealpreference.setFocusableInTouchMode(true);
        txname.setFocusableInTouchMode(true);
        txnickname.setFocusableInTouchMode(true);
        txpassportexpiry.setFocusableInTouchMode(true);
        txpassportnumber.setFocusableInTouchMode(true);
        txphonenumber.setFocusableInTouchMode(true);
        txpincode.setFocusableInTouchMode(true);
        txroomtype.setFocusableInTouchMode(true);
        txroomtypeperextranight.setFocusableInTouchMode(true);
        txstate.setFocusableInTouchMode(true);
        txtypeofaccomodation.setFocusableInTouchMode(true);
        txvisastatus.setFocusableInTouchMode(true);
        txwhatsappnumber.setFocusableInTouchMode(true);
        txcredaimember.setFocusableInTouchMode(true);
        txarrivaldate.setFocusableInTouchMode(true);
        txchildren.setFocusableInTouchMode(true);
        txtwinspoucechapter.setFocusableInTouchMode(true);
        txtwinspoucedob.setFocusableInTouchMode(true);
        txtwinspouceeamil.setFocusableInTouchMode(true);
        txtwinspoucemealpreference.setFocusableInTouchMode(true);
        txtwinspoucename.setFocusableInTouchMode(true);
        txtwinspoucepassportnumber.setFocusableInTouchMode(true);
        txtwinspoucephonenumber.setFocusableInTouchMode(true);

        btnEditButton.setVisibility(View.GONE);
    }
}
