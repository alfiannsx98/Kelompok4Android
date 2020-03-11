package com.example.usingprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        deklarasi dan menginisialisasi variable nama dengan label nama dari layout mainActivity
        TextView nama = findViewById(R.id.tv_namaMain);

//        men-set label nama dengan data user sedang login dari preferences
        nama.setText(Preferences.getLoggedInUser(getBaseContext()));

//        men-set status dan user yang sedang login menjadi default atau kosong di
//        data preferences. kemudian menuju ke login activity

        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                menghapus status login dan kembali ke login activity
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }
}
