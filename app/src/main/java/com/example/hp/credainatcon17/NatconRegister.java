package com.example.hp.credainatcon17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class NatconRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natcon_register);
    }

    public void Logout(View view) {

        FirebaseAuth.getInstance().signOut();
    }
}
