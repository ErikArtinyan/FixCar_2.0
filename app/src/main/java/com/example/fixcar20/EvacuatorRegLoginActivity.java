package com.example.fixcar20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EvacuatorRegLoginActivity extends AppCompatActivity {

    TextView evacuatorStatus, question;
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;
    DatabaseReference EvacuatorDatabaseRef;
    String OnlineEvacuatorID;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evacuator_reg_login);

        evacuatorStatus = (TextView) findViewById(R.id.evacuatorStatus);
        question = (TextView) findViewById(R.id.accauntCreate);
        signInBtn = (Button) findViewById(R.id.signInEvacuator);
        signUpBtn = (Button) findViewById(R.id.signUpEvacuator);
        emailET = (EditText) findViewById(R.id.evacuatorEmail);
        passwordET = (EditText) findViewById(R.id.evacuatorPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        signUpBtn.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(false);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                evacuatorStatus.setText("Регистрация для эвакуаторщиков");
                evacuatorStatus.setTextSize(20);

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterEvacuator(email, password);
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInEvacuator(email, password);

            }
        });

    }

    private void SignInEvacuator(String email, String password)
    {
        loadingBar.setTitle("Вход эвакуаторщика");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(EvacuatorRegLoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent evacuatorIntent = new Intent(EvacuatorRegLoginActivity.this, EvacuatorMapsActivity.class);
                    startActivity(evacuatorIntent);
                }
                else {
                    Toast.makeText(EvacuatorRegLoginActivity.this, "Произошла ошибка, попробуйте снова", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterEvacuator(String email, String password) {
        loadingBar.setTitle("Регистрация эвакуаторщика");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    OnlineEvacuatorID = mAuth.getCurrentUser().getUid();
                    Log.d("Firbase","Firbase");
                    EvacuatorDatabaseRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("Evacuators").child(OnlineEvacuatorID);


                    // Здесь вы можете добавить дополнительные данные, которые вы хотите сохранить в базе данных
                    // Например:
                    EvacuatorDatabaseRef.child("email").setValue(email);

                    Intent evacuatorIntent = new Intent(EvacuatorRegLoginActivity.this, EvacuatorMapsActivity.class);
                    startActivity(evacuatorIntent);

                    Toast.makeText(EvacuatorRegLoginActivity.this, " Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                } else {
                    Toast.makeText(EvacuatorRegLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}
