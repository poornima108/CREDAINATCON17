package com.example.hp.credainatcon17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NatconRegister3 extends AppCompatActivity {
    private RadioGroup rgTypeOfRoomSharing, rgTypeOfRoomSharing1, rgTypeOfRoomSharing2, rgTwinSharingPrice, rgSingleOccupencyPrice, rgSpouceOccupencyPrice;
    private RadioButton rbtnTypeOfRoomSharing_TwinSharing, rbtnTypeOfRoomSharing_SingleOccupency, rbtnTypeOfRoomSharing_SpouceOccupency;
    private CheckBox chkCredaiYouthMember;
    private RadioGroup rgTwinSharingPrice_discounted, rgSingleOccupencyPrice_discounted, rgSpouceOccupencyPrice_discounted, rgNatcon_extranight;
    private TextView roomextranight_textview;

    private TextView extranight_dinner_textview, extranight_dinner_textview_10th, txtNatcon_extranightdinner_10th, gala_dinner_textview_11th, txtNatcon_galadinner_11th;
    private Button btnNatcon_extranightdinner_10th_minus, btnNatcon_extranightdinner_10th_plus, btnNatcon_galadinner_11th_minus, btnNatcon_galadinner_11th_plus;
    private int extrannightdinner = 0;
    private int galanight = 0;

    DatabaseReference databaseReference, databaseReference1;
    String uid;
    private RadioButton rbtnExtranight_roomtype_superior, rbtnExtranight_roomtype_studio;
    private CheckBox chkNatcon_extranightroom;
    private String roomtypeextranight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register3);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations/" + uid);
        databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users/" + uid);

        rgTypeOfRoomSharing = (RadioGroup) findViewById(R.id.rgNatcon_typeofroomsharing);
        rgTypeOfRoomSharing1 = (RadioGroup) findViewById(R.id.rgNatcon_typeofroomsharing);
        rgTypeOfRoomSharing2 = (RadioGroup) findViewById(R.id.rgNatcon_typeofroomsharing);
        rgTwinSharingPrice = (RadioGroup) findViewById(R.id.rgNatcon_twinsharingprice);
        rgSingleOccupencyPrice = (RadioGroup) findViewById(R.id.rgNatcon_singleOccupencyprice);
        rgSpouceOccupencyPrice = (RadioGroup) findViewById(R.id.rgNatcon_spouceOccupencyprice);

        rbtnTypeOfRoomSharing_SingleOccupency = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_singleoccupancy);
        rbtnTypeOfRoomSharing_SpouceOccupency = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_spouceoccupency);
        rbtnTypeOfRoomSharing_TwinSharing = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_twinsharing);

        chkCredaiYouthMember = (CheckBox) findViewById(R.id.chkNatcon_credaiyouthmember);

        rgSingleOccupencyPrice_discounted = (RadioGroup) findViewById(R.id.rgNatcon_singleOccupencypricediscounted);
        rgSpouceOccupencyPrice_discounted = (RadioGroup) findViewById(R.id.rgNatcon_spouceOccupencypricediscounted);
        rgTwinSharingPrice_discounted = (RadioGroup) findViewById(R.id.rgNatcon_twinsharingpricediscounted);

        roomextranight_textview = (TextView) findViewById(R.id.room_extra_night_textview);
        rgNatcon_extranight = (RadioGroup) findViewById(R.id.rgNatcon_extranight);

        extranight_dinner_textview = (TextView) findViewById(R.id.extranight_dinner_textview);
        extranight_dinner_textview_10th = (TextView) findViewById(R.id.extranight_dinner_textview_10th);
        txtNatcon_extranightdinner_10th = (TextView) findViewById(R.id.txtNatcon_extranightdinner_10th);
        gala_dinner_textview_11th = (TextView) findViewById(R.id.gala_dinner_textview_11th);
        txtNatcon_galadinner_11th = (TextView) findViewById(R.id.txtNatcon_galadinner_11th);
        btnNatcon_extranightdinner_10th_minus = (Button) findViewById(R.id.btnNatcon_extranightdinner_10th_minus);
        btnNatcon_extranightdinner_10th_plus = (Button) findViewById(R.id.btnNatcon_extranightdinner_10th_plus);
        btnNatcon_galadinner_11th_minus = (Button) findViewById(R.id.btnNatcon_galadinner_11th_minus);
        btnNatcon_galadinner_11th_plus = (Button) findViewById(R.id.btnNatcon_galadinner_11th_plus);
        rbtnExtranight_roomtype_superior = (RadioButton) findViewById(R.id.rbtnNatcon_extranight_superior);
        rbtnExtranight_roomtype_studio = (RadioButton) findViewById(R.id.rbtnNatcon_extranight_studio);

        chkNatcon_extranightroom = (CheckBox) findViewById(R.id.chkNatcon_extranightroom);
        rbtnExtranight_roomtype_superior.setEnabled(false);
        rbtnExtranight_roomtype_studio.setEnabled(false);

    }

    @Override
    protected void onStart() {
        super.onStart();

        chkNatcon_extranightroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkNatcon_extranightroom.isChecked()) {
                    rbtnExtranight_roomtype_superior.setEnabled(true);
                    rbtnExtranight_roomtype_studio.setEnabled(true);
                } else {
                    rbtnExtranight_roomtype_superior.setEnabled(false);
                    rbtnExtranight_roomtype_studio.setEnabled(false);
                    rbtnExtranight_roomtype_superior.setChecked(false);
                    rbtnExtranight_roomtype_studio.setChecked(false);
                    roomtypeextranight = "notneeded";
                }
            }
        });


        rgTypeOfRoomSharing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                roomextranight_textview.setVisibility(View.VISIBLE);
                rgNatcon_extranight.setVisibility(View.VISIBLE);

                extranight_dinner_textview.setVisibility(View.VISIBLE);
                extranight_dinner_textview_10th.setVisibility(View.VISIBLE);
                txtNatcon_extranightdinner_10th.setVisibility(View.VISIBLE);
                gala_dinner_textview_11th.setVisibility(View.VISIBLE);
                txtNatcon_galadinner_11th.setVisibility(View.VISIBLE);
                btnNatcon_extranightdinner_10th_minus.setVisibility(View.VISIBLE);
                btnNatcon_extranightdinner_10th_plus.setVisibility(View.VISIBLE);
                btnNatcon_galadinner_11th_minus.setVisibility(View.VISIBLE);
                btnNatcon_galadinner_11th_plus.setVisibility(View.VISIBLE);

                if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
                    rgTwinSharingPrice.setVisibility(View.VISIBLE);

                    rgSingleOccupencyPrice.setVisibility(View.GONE);
                    rgSpouceOccupencyPrice.setVisibility(View.GONE);

                    rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);
                    rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);
                } else if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
                    rgSingleOccupencyPrice.setVisibility(View.VISIBLE);

                    rgTwinSharingPrice.setVisibility(View.GONE);
                    rgSpouceOccupencyPrice.setVisibility(View.GONE);

                    rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                    rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);
                } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
                    rgSpouceOccupencyPrice.setVisibility(View.VISIBLE);

                    rgTwinSharingPrice.setVisibility(View.GONE);
                    rgSingleOccupencyPrice.setVisibility(View.GONE);

                    rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                    rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);
                }
            }
        });
        chkCredaiYouthMember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                roomextranight_textview.setVisibility(View.VISIBLE);
                rgNatcon_extranight.setVisibility(View.VISIBLE);

                rgTypeOfRoomSharing.setOnCheckedChangeListener(null);
                rgTypeOfRoomSharing1.setOnCheckedChangeListener(null);
                rgTypeOfRoomSharing2.setOnCheckedChangeListener(null);
                if (chkCredaiYouthMember.isChecked()) {
                    rgTypeOfRoomSharing1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            extranight_dinner_textview.setVisibility(View.VISIBLE);
                            extranight_dinner_textview_10th.setVisibility(View.VISIBLE);
                            txtNatcon_extranightdinner_10th.setVisibility(View.VISIBLE);
                            gala_dinner_textview_11th.setVisibility(View.VISIBLE);
                            txtNatcon_galadinner_11th.setVisibility(View.VISIBLE);
                            btnNatcon_extranightdinner_10th_minus.setVisibility(View.VISIBLE);
                            btnNatcon_extranightdinner_10th_plus.setVisibility(View.VISIBLE);
                            btnNatcon_galadinner_11th_minus.setVisibility(View.VISIBLE);
                            btnNatcon_galadinner_11th_plus.setVisibility(View.VISIBLE);

                            if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
                                rgTwinSharingPrice_discounted.setVisibility(View.VISIBLE);

                                rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);

                                rgSingleOccupencyPrice.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice.setVisibility(View.GONE);
                            } else if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
                                rgSingleOccupencyPrice_discounted.setVisibility(View.VISIBLE);

                                rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);

                                rgTwinSharingPrice.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice.setVisibility(View.GONE);
                            } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
                                rgSpouceOccupencyPrice_discounted.setVisibility(View.VISIBLE);

                                rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                                rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);

                                rgTwinSharingPrice.setVisibility(View.GONE);
                                rgSingleOccupencyPrice.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    rgTypeOfRoomSharing2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            roomextranight_textview.setVisibility(View.VISIBLE);
                            rgNatcon_extranight.setVisibility(View.VISIBLE);

                            extranight_dinner_textview.setVisibility(View.VISIBLE);
                            extranight_dinner_textview_10th.setVisibility(View.VISIBLE);
                            txtNatcon_extranightdinner_10th.setVisibility(View.VISIBLE);
                            gala_dinner_textview_11th.setVisibility(View.VISIBLE);
                            txtNatcon_galadinner_11th.setVisibility(View.VISIBLE);
                            btnNatcon_extranightdinner_10th_minus.setVisibility(View.VISIBLE);
                            btnNatcon_extranightdinner_10th_plus.setVisibility(View.VISIBLE);
                            btnNatcon_galadinner_11th_minus.setVisibility(View.VISIBLE);
                            btnNatcon_galadinner_11th_plus.setVisibility(View.VISIBLE);

                            if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
                                rgTwinSharingPrice.setVisibility(View.VISIBLE);

                                rgSingleOccupencyPrice.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice.setVisibility(View.GONE);

                                rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);
                            } else if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
                                rgSingleOccupencyPrice.setVisibility(View.VISIBLE);

                                rgTwinSharingPrice.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice.setVisibility(View.GONE);

                                rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                                rgSpouceOccupencyPrice_discounted.setVisibility(View.GONE);
                            } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
                                rgSpouceOccupencyPrice.setVisibility(View.VISIBLE);

                                rgTwinSharingPrice.setVisibility(View.GONE);
                                rgSingleOccupencyPrice.setVisibility(View.GONE);

                                rgTwinSharingPrice_discounted.setVisibility(View.GONE);
                                rgSingleOccupencyPrice_discounted.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
        btnNatcon_extranightdinner_10th_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extrannightdinner != 0) {
                    extrannightdinner--;
                }
                txtNatcon_extranightdinner_10th.setText("" + extrannightdinner);
            }
        });
        btnNatcon_extranightdinner_10th_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extrannightdinner++;
                txtNatcon_extranightdinner_10th.setText("" + extrannightdinner);
            }
        });
        btnNatcon_galadinner_11th_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (galanight != 0) {
                    galanight--;
                }
                txtNatcon_galadinner_11th.setText("" + galanight);
            }
        });
        btnNatcon_galadinner_11th_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galanight++;
                txtNatcon_galadinner_11th.setText("" + galanight);
            }
        });

    }

    public void NoHotelRegistration(View view) {
        databaseReference.child("credaiyouthmember").setValue(chkCredaiYouthMember.isChecked() ? "yes" : "no");
        databaseReference.child("typeofaccomodation").setValue("not needed");
        databaseReference.child("roomtype").setValue("not needed");
        databaseReference.child("roomtypeextranight").setValue("not needed");
        databaseReference.child("10thdinner").setValue("not needed");
        databaseReference.child("galanight").setValue("not needed");
        startActivity(new Intent(NatconRegister3.this, HomeScreen.class));
    }

    public void Next3(View view) {
        String roomtype = null;
        roomtypeextranight = "notneeded";
        String x = ((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString();


        if (x.isEmpty()) {
            Toast.makeText(this, "Click The above Button to skip hotel registration", Toast.LENGTH_SHORT).show();
            x = "notneeded";
            databaseReference.child("typeofaccomodation").setValue(x);

        } else {
            if (chkCredaiYouthMember.isChecked()) {
                if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgSingleOccupencyPrice_discounted.getCheckedRadioButtonId())).getText().toString();
                } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgSpouceOccupencyPrice_discounted.getCheckedRadioButtonId())).getText().toString();
                } else if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgTwinSharingPrice_discounted.getCheckedRadioButtonId())).getText().toString();
                }
            } else {
                if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgSingleOccupencyPrice.getCheckedRadioButtonId())).getText().toString();
                } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgSpouceOccupencyPrice.getCheckedRadioButtonId())).getText().toString();
                } else if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
                    roomtype = ((RadioButton) findViewById(rgTwinSharingPrice.getCheckedRadioButtonId())).getText().toString();
                }
            }

            if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())) == null) {
                roomtype = "not needed";
            } else {
                if (roomtype.startsWith("Superior")) {
                    roomtype = "superior";
                } else {
                    roomtype = "studio";
                }
            }

            if (((RadioButton) findViewById(rgNatcon_extranight.getCheckedRadioButtonId())) == null) {
                roomtypeextranight = "not needed";
            } else {
                if (((RadioButton) findViewById(rgNatcon_extranight.getCheckedRadioButtonId())).getText().toString().startsWith("Superior")) {
                    roomtypeextranight = "superior";
                } else {
                    roomtypeextranight = "studio";
                }
            }

            databaseReference.child("credaiyouthmember").setValue(chkCredaiYouthMember.isChecked() ? "yes" : "no");
            databaseReference.child("typeofaccomodation").setValue(x);
            databaseReference.child("roomtype").setValue(roomtype);
            databaseReference.child("roomtypeextranight").setValue(roomtypeextranight);
            databaseReference.child("10thdinner").setValue(txtNatcon_extranightdinner_10th.getText().toString());
            databaseReference.child("galanight").setValue(txtNatcon_galadinner_11th.getText().toString());


            Toast.makeText(this, "NATCON REGISTRATION COMPLETED", Toast.LENGTH_SHORT).show();
            if (x.equals("Twin Sharing") || x.equals("Spouce Occupency")) {
                startActivity(new Intent(NatconRegister3.this, NatconRegister4.class));
                finish();
            } else {
                databaseReference1.child("natconregistered").setValue("yes");
                startActivity(new Intent(NatconRegister3.this, NatconRegistrationSummary.class));
                finish();
            }
        }
    }
}


