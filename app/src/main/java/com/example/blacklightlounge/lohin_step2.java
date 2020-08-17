package com.example.blacklightlounge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class lohin_step2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohin_step2);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();



    }
}