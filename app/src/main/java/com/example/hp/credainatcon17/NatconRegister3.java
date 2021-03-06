package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private RadioGroup rgTypeOfRoomSharing;
    private RadioButton rbtnTypeOfRoomSharing_TwinSharing, rbtnTypeOfRoomSharing_SingleOccupency, rbtnTypeOfRoomSharing_SpouceOccupency;
    private CheckBox chkCredaiYouthMember;
    private RadioGroup rgNatcon_extranight;

    private TextView txtNatcon_extranightdinner_10th, txtNatcon_galadinner_11th;
    private Button btnNatcon_extranightdinner_10th_minus, btnNatcon_extranightdinner_10th_plus, btnNatcon_galadinner_11th_minus, btnNatcon_galadinner_11th_plus;
    private int extrannightdinner = 0;
    private int galanight = 0;

    private DatabaseReference databaseReference, databaseReference1;
    private String uid;
    private RadioButton rbtnExtranight_roomtype_superior, rbtnExtranight_roomtype_studio;
    private CheckBox chkNatcon_extranightroom;
    private String roomtypeextranight;
    private RadioButton rbtnTypeOfRoomSHaringPrice_studio;
    private RadioButton rbtnTypeOfRoomSHaringPrice_superior;
    private RadioGroup rgTypeOfRoomSharingPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register3);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/natconregistrations/" + uid);
        databaseReference1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users/" + uid);

        rgTypeOfRoomSharing = (RadioGroup) findViewById(R.id.rgNatcon_typeofroomsharing);

        rbtnTypeOfRoomSharing_SingleOccupency = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_singleoccupancy);
        rbtnTypeOfRoomSharing_SpouceOccupency = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_spouceoccupency);
        rbtnTypeOfRoomSharing_TwinSharing = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharing_twinsharing);
        //************************************************************************************************************************
        rbtnTypeOfRoomSHaringPrice_superior = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharingprice_superior);
        rbtnTypeOfRoomSHaringPrice_studio = (RadioButton) findViewById(R.id.rbtnNatcon_typeofroomsharingprice_studio);
        rgTypeOfRoomSharingPrice = (RadioGroup) findViewById(R.id.rgNatcon_typeofroomsharingprice);

        //*************************************************************************************************************************
        chkCredaiYouthMember = (CheckBox) findViewById(R.id.chkNatcon_credaiyouthmember);

        rgNatcon_extranight = (RadioGroup) findViewById(R.id.rgNatcon_extranight);
        rbtnExtranight_roomtype_superior = (RadioButton) findViewById(R.id.rbtnNatcon_extranight_superior);
        rbtnExtranight_roomtype_studio = (RadioButton) findViewById(R.id.rbtnNatcon_extranight_studio);
        chkNatcon_extranightroom = (CheckBox) findViewById(R.id.chkNatcon_extranightroom);

        txtNatcon_extranightdinner_10th = (TextView) findViewById(R.id.txtNatcon_extranightdinner_10th);
        txtNatcon_galadinner_11th = (TextView) findViewById(R.id.txtNatcon_galadinner_11th);

        btnNatcon_extranightdinner_10th_minus = (Button) findViewById(R.id.btnNatcon_extranightdinner_10th_minus);
        btnNatcon_extranightdinner_10th_plus = (Button) findViewById(R.id.btnNatcon_extranightdinner_10th_plus);
        btnNatcon_galadinner_11th_minus = (Button) findViewById(R.id.btnNatcon_galadinner_11th_minus);
        btnNatcon_galadinner_11th_plus = (Button) findViewById(R.id.btnNatcon_galadinner_11th_plus);


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
                    rbtnExtranight_roomtype_superior.setChecked(false);
                    rbtnExtranight_roomtype_studio.setChecked(false);
                    rbtnExtranight_roomtype_superior.setChecked(true);
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
                if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Twin Sharing")) {
                    rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 2000000");
                    rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 1000000");
                } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Single Occupancy")) {
                    rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 4000000");
                    rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 3000000");
                } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Spouce Occupancy")) {
                    rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 6000000");
                    rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 5000000");
                }
            }
        });
        chkCredaiYouthMember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkCredaiYouthMember.isChecked()) {
                    rgTypeOfRoomSharing.setOnCheckedChangeListener(null);
                    if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Twin Sharing")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 2500000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 1500000");
                    } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Single Occupancy")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 4000000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 3000000");
                    } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Spouce Occupancy")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 6000000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 5000000");
                    }
                    rgTypeOfRoomSharing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Twin Sharing")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 2500000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 1500000");
                            } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Single Occupancy")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 4000000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 3000000");
                            } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Spouce Occupancy")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 6000000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 5000000");
                            }
                        }
                    });

                } else {
                    rgTypeOfRoomSharing.setOnCheckedChangeListener(null);
                    if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Twin Sharing")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 2000000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 1000000");
                    } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Single Occupancy")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 4000000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 3000000");
                    } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Spouce Occupancy")) {
                        rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 6000000");
                        rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 5000000");
                    }
                    rgTypeOfRoomSharing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Twin Sharing")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 2000000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 1000000");
                            } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Single Occupancy")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 4000000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 3000000");
                            } else if (((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString().equals("Spouce Occupancy")) {
                                rbtnTypeOfRoomSHaringPrice_superior.setText("Superior INR 6000000");
                                rbtnTypeOfRoomSHaringPrice_studio.setText("Superior INR 5000000");
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
                if (extrannightdinner < 10) {
                    extrannightdinner++;
                }
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
                if (galanight < 10) {
                    galanight++;
                }
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
        finish();
    }

    public void Next3(View view) {
        String roomtype = null;
        roomtypeextranight = "notneeded";
        String x = ((RadioButton) findViewById(rgTypeOfRoomSharing.getCheckedRadioButtonId())).getText().toString();


        if (rbtnTypeOfRoomSharing_SingleOccupency.isChecked()) {
            roomtype = ((RadioButton) findViewById(rgTypeOfRoomSharingPrice.getCheckedRadioButtonId())).getText().toString();
        } else if (rbtnTypeOfRoomSharing_SpouceOccupency.isChecked()) {
            roomtype = ((RadioButton) findViewById(rgTypeOfRoomSharingPrice.getCheckedRadioButtonId())).getText().toString();
        } else if (rbtnTypeOfRoomSharing_TwinSharing.isChecked()) {
            roomtype = ((RadioButton) findViewById(rgTypeOfRoomSharingPrice.getCheckedRadioButtonId())).getText().toString();
        }


        if (roomtype.startsWith("Superior")) {
            roomtype = "superior";
        } else {
            roomtype = "studio";
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


        if (x.equals("Twin Sharing") || x.equals("Spouce Occupancy")) {
            startActivity(new Intent(NatconRegister3.this, NatconRegister4.class));
            finish();
        } else {
            Toast.makeText(this, "NATCON REGISTRATION COMPLETED", Toast.LENGTH_SHORT).show();
            databaseReference1.child("natconregistered").setValue("yes");
            startActivity(new Intent(NatconRegister3.this, NatconRegistrationSummary.class));
            finish();
        }

    }
}


