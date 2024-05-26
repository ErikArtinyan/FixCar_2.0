package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixcar20.Mode_Selection;
import com.example.fixcar20.Question_Capitals_View_Activity;
import com.example.fixcar20.Question_Flags_View_Activty;
import com.example.fixcar20.Question_Maps_View_Activity;
import com.example.fixcar20.R;

public class End extends AppCompatActivity {
    TextView maxScoreCount;
    TextView scoreCount;
    ImageButton tryAgainButton;
    ImageButton homeButton;
    String context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        maxScoreCount = findViewById(R.id.maxScoreCount);
        scoreCount = findViewById(R.id.scoreCount);
        tryAgainButton = findViewById(R.id.try_again);
        homeButton = findViewById(R.id.home);

        if (getIntent().getStringExtra("bals") != null){
            scoreCount.setText(getIntent().getStringExtra("bals"));

        }
        if (getIntent().getStringExtra("maxBals") != null){
            maxScoreCount.setText(getIntent().getStringExtra("maxBals"));
           // Toast.makeText(this, String.valueOf(getIntent().getStringExtra("maxBals")), Toast.LENGTH_SHORT).show();
        }else{
           // Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        if (getIntent().getStringExtra("context") != null){
            context = (String) getIntent().getStringExtra("context");
        }
        if(getIntent().getStringExtra("uraa") != null){
            TextView uraa = findViewById(R.id.uraaa);
            uraa.setVisibility(View.VISIBLE);
        }
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(End.this, Mode_Selection.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context != null){
                    if(context.equals("Question_Capitals_View_Activity")){
                        Intent intent = new Intent(End.this, Question_Capitals_View_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    if(context.equals("Question_Flags_View_Activty")){
                        Intent intent = new Intent(End.this, Question_Flags_View_Activty.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    if(context.equals("Question_Maps_View_Activity")){
                        Intent intent = new Intent(End.this, Question_Maps_View_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

    }


}