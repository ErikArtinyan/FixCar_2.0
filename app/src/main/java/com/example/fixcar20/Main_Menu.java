package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void next(View view) {
        Intent intent = new Intent(Main_Menu.this,Mode_Selection.class);
        startActivity(intent);
    }
}