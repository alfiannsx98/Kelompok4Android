package com.example.cobavolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText FullName, Email, Password;
    Button Register;
    RequestQueue requestQueue;
    String NameHolder, EmailHolder, PasswordHolder;
    ProgressDialog progressDialog;
    String HttpUrl = "http://192.168.1.31/valley/user-register";
    private boolean CheckEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FullName = (EditText) findViewById(R.id.EditTextFullName);
        Email = (EditText) findViewById(R.id.EditTextEmail);
        Password = (EditText) findViewById(R.id.EditTextPassword);

        Register = (Button) findViewById(R.id.ButtonRegister);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog = new ProgressDialog(MainActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ButtonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    UserRegistration();
                }else{
                    Toast.makeText(MainActivity.this, "Tolong Diisi Semua Fieldnya!", Toast.LENGTH_LONG).show();
                }
            }
        });

//        Register.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CheckEditTextIsEmptyOrNot();
//
//                if(CheckEditText){
//                    UserRegistration();
//                }else{
//                    Toast.makeText(MainActivity.this, "Tolong Diisi Semua Fieldnya!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
    public void CheckEditTextIsEmptyOrNot(){
        NameHolder = FullName.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }else{
            CheckEditText = true;
        }
    }

    public void UserRegistration(){

        progressDialog.setMessage("Tolong Tunggu, Kami sedang memasukkan data anda kedalam server");
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String ServerResponse) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("user_email", EmailHolder);
                params.put("user_password", PasswordHolder);
                params.put("user_full_name", NameHolder);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
