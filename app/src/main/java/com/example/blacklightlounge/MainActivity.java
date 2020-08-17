package com.example.blacklightlounge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser User;

    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();

        enter=findViewById(R.id.btn_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User == null) {
                    Intent homeIntent = new Intent(MainActivity.this, lohin_step1.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeIntent);
                    finish();
                } else{
                    Intent homeIntent = new Intent(MainActivity.this, end_qr.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}
