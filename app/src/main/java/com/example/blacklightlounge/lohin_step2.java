package com.example.blacklightlounge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class lohin_step2 extends AppCompatActivity {

    private Button enter;
    private TextView OTP;

    private FirebaseAuth mAuth;
    private FirebaseUser User;
    private String mAuthCredentials;
    private String name;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohin_step2);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        enter = findViewById(R.id.btn_enter);
        OTP = findViewById(R.id.code);

        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();

        name = getIntent().getStringExtra("phone");
        mAuthCredentials = getIntent().getStringExtra("AuthCredentials");

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = OTP.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthCredentials, otp);
                signInWithPhoneAuthCredential(credential);
            }


        });
    }

        private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(lohin_step2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User = mAuth.getCurrentUser();
                                Map<String, Object> user = new HashMap<>();
                                user.put("points", 0);
                                Intent homeIntent = new Intent(lohin_step2.this, end_qr.class);
                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(homeIntent);
                                finish();
                               /* db.collection("users")
                                        .document(name)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {

                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                                Intent homeIntent = new Intent(lohin_step2.this, end_qr.class);
                                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(homeIntent);
                                                finish();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error writing document", e);
                                                enter.setText("Error");
                                            }
                                        });*/
                            }

                        }
                    });
        }



    }
