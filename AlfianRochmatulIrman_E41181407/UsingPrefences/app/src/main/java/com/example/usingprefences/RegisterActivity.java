package com.example.usingprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    
    private EditText mViewUser, mViewPassword, mViewRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
//        Menginisialisasi variable dengan Form User, Form Password dan Form RePassword
//        dari layout registerActivity
        mViewUser = findViewById(R.id.et_emailSignup);
        mViewPassword = findViewById(R.id.et_passwordSignup);
        mViewRePassword = findViewById(R.id.et_passwordSignup2);
        
//        menjalankan method razia() jika merasakan tombol signup di keyboard disentuh
        mViewRePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    razia();
                    return true;
                }
                return false;
            }
        });
//        Menjalankan Method razia() jika merasakan tombol signUp disentuh
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }
//  Men check inputan user dan password dan memberikan akses ke mainactivity
    private void razia() {
        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRePassword.setError(null);
        View fokus = null;
        boolean cancel = false;

//        mengambil text dari form user, password, repassword dengan variable baru bertipe String
        String repassword = mViewRePassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

//        jika form user kosong atau memenuhi kriteria di method cekuser() maka set error di form
//        user dengan menset variable fokus dan error di viewnya juga cancel maenjadi true.

        if(TextUtils.isEmpty(user)){
            mViewUser.setError("Field ini gak boleh kosong!");
            fokus = mViewUser;
            cancel = true;
        }else if(cekUser(user)){
            mViewUser.setError("Username telah tersedia");
            fokus = mViewUser;
            cancel = true;
        }

//        jika form password kosong dan memenuhi kriteria di method cekpassword maka, reaksinya
//        sama dengan percabangan user di atas, bedanya untuk password dan repassword

        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("Gak Boleh Kosong!");
            fokus = mViewPassword;
            cancel = true;
        }else if(!cekPassword(password, repassword)){
            mViewRePassword.setError("Password ini salah!");
            fokus = mViewRePassword;
            cancel = true;
        }

//        Jika cancel true, variable fokus mendapatkan fokus, jika false, maka
//        kembali ke loginactivity dan set user dan password untuk data yang terdaftar
        if(cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(), user);
            Preferences.setRegisterPass(getBaseContext(), password);
            finish();
        }
    }
    //        true jika parameter password sama dengan parameter repassword
    private boolean cekPassword(String password, String repassword) {
        return password.equals(repassword);
    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
