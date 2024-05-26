package com.example.fixcar20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Main_Menu extends AppCompatActivity {

    private Button LogoutButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String customerID;

    public static TextView bali;
    public static int bal = 0;
    long bal1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        LogoutButton = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        bali = findViewById(R.id.bali);



        DocumentReference userDocument = FirebaseFirestore.getInstance().collection("users").document(mAuth.getCurrentUser().getUid());
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Получаем значение поля "bal" из документа

                        bali.setText(Long.toString( document.getLong("bal")));

                    } else {
                        Log.d("Firestore", "No such document");
                    }
                } else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });




        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent welcomeIntent = new Intent(Main_Menu.this, CustomerRegLoginActivity.class);
                startActivity(welcomeIntent);
                finish();


            }
        });
    }

    public void next(View view) {

        Intent intent = new Intent(Main_Menu.this,Mode_Selection.class);
        startActivity(intent);
        finish();

    }

    public void Leader(View view) {

        Intent intent = new Intent(Main_Menu.this,Lideri.class);
        startActivity(intent);
        finish();

    }
}