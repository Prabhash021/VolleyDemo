package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    EditText fname, sname;
    Button send;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fname = findViewById(R.id.fName);
        sname = findViewById(R.id.sName);
        send = findViewById(R.id.btn);
        result = findViewById(R.id.result);


    }
}