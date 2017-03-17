package com.example.hp.credainatcon17;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotLoginPassword extends AppCompatActivity {
    private EditText edtForgotLoginPassword_email;
    private FirebaseAuth firebaseAuth;
    private MaterialDialog mDialog;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login_password);
        edtForgotLoginPassword_email = (EditText) findViewById(R.id.edtForgotLoginPassword_email);
        firebaseAuth = FirebaseAuth.getInstance();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.edtForgotLoginPassword_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);


        MaterialDialog.Builder builder = new MaterialDialog.Builder(ForgotLoginPassword.this)
                .title("RESET SUCCESSFULL")
                .content("A PASSWORD RESET MAIL HAS BEEN SENT TO THE PROVIDED EMAIL ADDRESS,PLEASE CLICK THE LINK IN THE EMAIL TO RESET YOUR PASSWORD.")
                .positiveText("OK");
        mDialog = builder.build();
    }

    public void redirectLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    public void resetLoginPassword(View view) {
        if (awesomeValidation.validate()) {
            firebaseAuth.sendPasswordResetEmail(edtForgotLoginPassword_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        mDialog.show();
                        new MaterialDialog.Builder(ForgotLoginPassword.this)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        mDialog.dismiss();
                                        startActivity(new Intent(ForgotLoginPassword.this, LoginActivity.class));
                                        finish();
                                    }

                                });
                    } else {
                        Toast.makeText(ForgotLoginPassword.this, "ENTER REGISTERED EMAIL OR CREATE NEW ACCOUNT", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public void redirectSignup(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
}
