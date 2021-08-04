package com.example.projectyb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Side_nologin extends AppCompatActivity {

    private TextView app_name4, side_login, side_join, side_mypage, side_info, side_Q;
    private TextView side_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_nologin);

        app_name4 = findViewById(R.id.app_name4);
        side_login = findViewById(R.id.side_login);
        side_join = findViewById(R.id.side_join);
        side_mypage = findViewById(R.id.side_mypage);
        side_info = findViewById(R.id.side_info);
        side_Q = findViewById(R.id.side_Q);

        side_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        side_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });

        side_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        side_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        side_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}