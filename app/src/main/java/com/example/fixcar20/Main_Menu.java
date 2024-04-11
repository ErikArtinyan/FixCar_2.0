package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Menu extends AppCompatActivity {

    private Button LogoutButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        LogoutButton = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
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

    }

}