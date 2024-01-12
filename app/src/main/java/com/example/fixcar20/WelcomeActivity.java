package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button customerBtn, evacuatorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        customerBtn = (Button)findViewById(R.id.customerBtn);
        evacuatorBtn = (Button)findViewById(R.id.evacuatorBtn);

        evacuatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent evacuatorIntent = new Intent(WelcomeActivity.this, EvacuatorRegLoginActivity.class);
                startActivity(evacuatorIntent);
            }
        });
        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(WelcomeActivity.this, CustomerRegLoginActivity.class);
                startActivity(customerIntent);
            }
        });

    }
}