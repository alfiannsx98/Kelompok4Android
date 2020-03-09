package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button mRegister = (Button) findViewById(R.id.bt_register);
        Button mBack = (Button) findViewById(R.id.bt_back);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"belum ada isi",Toast.LENGTH_LONG).show();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
            Intent iBack = new Intent(getApplication(), MainActivity.class);
            startActivity(iBack);
            finish();
            }
        });
    }
}
