package com.example.usingprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Meng inisialisasi variable dengan form user dan form password dari layout loginactivity
        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);
//        Menjalankan method razia() jika tombol signin di keyboard di sentuh
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    razia();
                    return true;
                }
                return false;
            }
        });
        //  Menjalankan method razia()jika merasakan tombol signin disentuh
        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
//        Ke registeractivity jika merasakan tombol signup disentuh
        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

//    Ke main activity jika data status login dari data preferences bernilai true
    @Override
    protected void onStart() {
        super.onStart();
        if(Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    private void razia() {
//        Mereset semua error dan fokus menjadi default
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

//        mengambil text dari form User dan form password dengan variable baru bertipe string
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

//        Jika form user kosong atau tidak memenuhi kriteria di method cekUser(), maka set Error di form
//        user dengan menset variable fokus dan error di viewnya juga cancel menjadi true

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("Field ini tidak boleh kosong");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("Username ini tidak ditemukan");
            fokus = mViewUser;
            cancel = true;
        }

        if(TextUtils.isEmpty(password)){
            mViewPassword.setError("Password nya salah");
            fokus = mViewPassword;
            cancel = true;
        }

//        jika cancel true, maka variable fokus mendapatkan fokus
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
