package com.example.blacklightlounge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_qr);
       // setContentView(R.layout.activity_lohin_step1);
       // setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
}
