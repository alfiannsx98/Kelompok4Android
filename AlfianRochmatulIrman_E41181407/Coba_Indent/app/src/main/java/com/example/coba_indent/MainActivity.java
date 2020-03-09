package com.example.coba_indent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.coba_indent.R.*;
import static com.example.coba_indent.R.id.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMoveActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        btnMoveActivity = findViewById(btn_move_activity);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_move_activity) {
            Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        }
    }
}
