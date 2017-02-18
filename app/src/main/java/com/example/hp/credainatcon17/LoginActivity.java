package com.example.hp.credainatcon17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email_id, pass_word;
    private Button log_in;
    private TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_id = (EditText) findViewById(R.id.email_id_login);
        pass_word = (EditText) findViewById(R.id.password_login);
        sign_up = (TextView) findViewById(R.id.account_sign_up);
        log_in = (Button) findViewById(R.id.login_button);
        sign_up.setOnClickListener(this);
        log_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == log_in) {
            loginUser();
        }
        if (view == sign_up) {
            signupUser();
        }
    }

    private void signupUser() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void loginUser() {
        startActivity(new Intent(this,ContactUs.class));

    }
}
