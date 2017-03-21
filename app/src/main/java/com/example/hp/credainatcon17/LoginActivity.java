package com.example.hp.credainatcon17;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText email_id, pass_word;
    private Button log_in;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private TextView signUp;
    boolean connected = false;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        mAuth = FirebaseAuth.getInstance();

        email_id = (EditText) findViewById(R.id.edtLogin_email);
        pass_word = (EditText) findViewById(R.id.edtLogin_password);
        log_in = (Button) findViewById(R.id.LoginActivityLoginButton);
        signUp = (TextView) findViewById(R.id.txtLogin_signup);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://credainatcon17-f96ad.firebaseio.com/users");

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                ConnectivityManager cm = (ConnectivityManager) LoginActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (firebaseAuth.getCurrentUser() != null) {
                    if (activeNetwork != null) {
                        String userId = firebaseAuth.getCurrentUser().getUid().toString();
                        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                handelLogin(dataSnapshot);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "PLEASE CONNECT TO INTERNET AND TRY AGAIN.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "YOU ARE NOT LOGGED IN", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        };
    }


    private void handelLogin(DataSnapshot dataSnapshot) {
        if (dataSnapshot.child("adminauth").getValue().toString().equals("pending")) {
            Toast.makeText(LoginActivity.this, "ADMIN AUTHORIZATION PENDING. TRY AGAIN LATER", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        } else if (dataSnapshot.child("adminauth").getValue().toString().equals("approved")) {
            if (dataSnapshot.child("natconregistered").getValue().toString().equals("no")) {
                startActivity(new Intent(LoginActivity.this, NatconRegister.class));
                finish();
            } else if (dataSnapshot.child("natconregistered").getValue().toString().equals("yes")) {
                if (dataSnapshot.child("paymentstatus").getValue().toString().equals("pending")) {
                    startActivity(new Intent(LoginActivity.this, PaymentGateway.class));
                } else if (dataSnapshot.child("paymentstatus").getValue().toString().equals("approved")) {
                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                }
            }
        } else if (dataSnapshot.child("adminauth").getValue().toString().equals("denied")) {
            Toast.makeText(LoginActivity.this, "YOU HAVE BEEN DENIED ACCESS TO THIS APP", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) LoginActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    startSignIn();
                } else {
                    Toast.makeText(LoginActivity.this, "Internet Not Available Please Turn On Your Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    private void startSignIn() {
        progressDialog.show();
        String email = email_id.getText().toString();
        String password = pass_word.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else {
            if (isValidEmail(email)) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Username or Password is invalid please enter a valid username or password", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        }
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(this, "PLEASE ENTER A VALID EMAIL", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void forgotPassword(View view) {

        startActivity(new Intent(this, ForgotLoginPassword.class));
    }
}
