package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mLogin, mKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mKembali =(Button)findViewById(R.id.btn_back);
        mLogin =(Button)findViewById(R.id.email_sign_in_button);

        mLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"belum ada isi",Toast.LENGTH_LONG).show();
            }
        });

        mKembali.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iKembali = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(iKembali);
                finish();
            }
        });
    }
}
