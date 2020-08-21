package com.example.blacklightlounge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TEST extends AppCompatActivity {

    private Button buton;
    private FirebaseFirestore db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_e_s_t);
        getSupportActionBar().hide();

        db1 = FirebaseFirestore.getInstance();
        buton.findViewById(R.id.button34);


        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db1.collection("users")
                        .document("+79778085487")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        buton.setText("OK");


                                    }else {
                                        buton.setText("NO");
                                        /*Map<String, Object> user = new HashMap<>();
                                        user.put("points", 0);
                                        db.collection("users")
                                                .document(User.getPhoneNumber())
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
                                                });
                                        Intent next = new Intent(lohin_step2.this, MainActivity.class);
                                        startActivity(next);*/
                                    }
                                }
                            }
                        })
                        .addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {

                            }
                        });
            }
        });
    }
}