package com.example.blacklightlounge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class lohin_step1 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser User;

    private String name;

    private ImageView back;
    private Button enter;
    private TextView phonenumber;


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohin_step1);
        getSupportActionBar().hide();

        back = findViewById(R.id.back_img);
        enter = findViewById(R.id.btn_enter);
        phonenumber = findViewById(R.id.name);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = phonenumber.getText().toString();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        name,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        lohin_step1.this,               // Activity (for callback binding)
                        mCallbacks);
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                enter.setText("Complited");
                Log.d("JEJE", "onVerificationCompleted:" + phoneAuthCredential);
                signInWithPhoneAuthCredential(phoneAuthCredential);

            }


            @Override
            public void onVerificationFailed(FirebaseException e) {
                enter.setText("Faled");
               /* bar.setVisibility(View.INVISIBLE);
                button1.setEnabled(true);
                error.setText("Пожалуйста введите номер \n В форме +79771234567");*/

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                enter.setText("sent");
                resendVerificationCode(s, forceResendingToken);
                Intent next = new Intent(lohin_step1.this, lohin_step2.class);
                next.putExtra("AuthCredentials", s);
                next.putExtra("phone", name);
                startActivity(next);
                finish();

            }


            private void resendVerificationCode(String phoneNumber,
                                                PhoneAuthProvider.ForceResendingToken token) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        lohin_step1.this,             // Activity (for callback binding)
                        mCallbacks,         // OnVerificationStateChangedCallbacks
                        token);        // resending
                // [END start_phone_auth]
            }

        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(lohin_step1.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(lohin_step1.this, MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(lohin_step1.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent homeIntent = new Intent(lohin_step1.this, MainActivity.class);
                            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(homeIntent);
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });

    }
}
