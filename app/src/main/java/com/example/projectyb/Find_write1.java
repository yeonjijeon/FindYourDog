package com.example.projectyb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Find_write1 extends AppCompatActivity {

    private TextView app_name4, tv_info, tv_tel, tv_date, tv_where, tv_img;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_plus, btn_ok;
    private EditText edt_find, edt_tel, edt_where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_write1);

        app_name4 = findViewById(R.id.app_name4);
        tv_info = findViewById(R.id.tv_info);
        tv_tel = findViewById(R.id.tv_tel);
        tv_date = findViewById(R.id.tv_date);
        tv_where = findViewById(R.id.tv_where);
        tv_img = findViewById(R.id.tv_img);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_plus = findViewById(R.id.btn_plus);
        btn_ok = findViewById(R.id.btn_ok);
        edt_find = findViewById(R.id.edt_find);
        edt_tel = findViewById(R.id.edt_tel);
        edt_where = findViewById(R.id.edt_where);


    }
}