//package com.example.fixcar20;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class EvacuatorRegLoginActivity extends AppCompatActivity {
//
//    TextView evacuatorStatus, question;
//    Button signInBtn, signUpBtn;
//    EditText emailET, passwordET, confirmPasswordET;
//
//    FirebaseAuth mAuth;
//    DatabaseReference EvacuatorDatabaseRef;
//    String OnlineEvacuatorID;
//
//    ProgressDialog loadingBar;
//
//    boolean isRegistering = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_evacuator_reg_login);
//
//        evacuatorStatus = findViewById(R.id.evacuatorStatus);
//        question = findViewById(R.id.accauntCreate);
//        signInBtn = findViewById(R.id.signInEvacuator);
//        signUpBtn = findViewById(R.id.signUpEvacuator);
//        emailET = findViewById(R.id.evacuatorEmail);
//        passwordET = findViewById(R.id.evacuatorPassword);
//        confirmPasswordET = findViewById(R.id.evacuatorConfirmPassword);
//
//        mAuth = FirebaseAuth.getInstance();
//        loadingBar = new ProgressDialog(this);
//
//        signUpBtn.setVisibility(View.INVISIBLE);
//        signUpBtn.setEnabled(false);
//        confirmPasswordET.setVisibility(View.GONE);
//
//        question.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isRegistering = !isRegistering;
//                updateUI();
//            }
//        });
//
//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailET.getText().toString();
//                String password = passwordET.getText().toString();
//                String confirmPassword = confirmPasswordET.getText().toString();
//
//                if (!password.equals(confirmPassword)) {
//                    Toast.makeText(EvacuatorRegLoginActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                RegisterEvacuator(email, password);
//            }
//        });
//
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailET.getText().toString();
//                String password = passwordET.getText().toString();
//
//                SignInEvacuator(email, password);
//            }
//        });
//
//        updateUI();
//    }
//
//    private void SignInEvacuator(String email, String password) {
//        loadingBar.setTitle("Вход эвакуаторщика");
//        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
//        loadingBar.show();
//
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    if (user != null && user.isEmailVerified()) {
//                        Toast.makeText(EvacuatorRegLoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//                        Intent evacuatorIntent = new Intent(EvacuatorRegLoginActivity.this, EvacuatorMapsActivity.class);
//                        startActivity(evacuatorIntent);
//                    } else {
//                        Toast.makeText(EvacuatorRegLoginActivity.this, "Пожалуйста, подтвердите свою электронную почту", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//                    }
//                } else {
//                    Toast.makeText(EvacuatorRegLoginActivity.this, "Произошла ошибка, попробуйте снова", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                }
//            }
//        });
//    }
//
//    private void RegisterEvacuator(String email, String password) {
//        loadingBar.setTitle("Регистрация эвакуаторщика");
//        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
//        loadingBar.show();
//
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    if (user != null) {
//                        OnlineEvacuatorID = user.getUid();
//                        EvacuatorDatabaseRef = FirebaseDatabase.getInstance().getReference()
//                                .child("Users").child("Evacuators").child(OnlineEvacuatorID);
//
//                        EvacuatorDatabaseRef.child("email").setValue(email);
//
//                        sendEmailVerification(user);
//
//                        Toast.makeText(EvacuatorRegLoginActivity.this, "Регистрация прошла успешно. Пожалуйста, подтвердите свою электронную почту", Toast.LENGTH_SHORT).show();
//                        loadingBar.dismiss();
//                    }
//                } else {
//                    Toast.makeText(EvacuatorRegLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                }
//            }
//        });
//    }
//
//    private void sendEmailVerification(FirebaseUser user) {
//        user.sendEmailVerification()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("TAG", "Email sent.");
//                        } else {
//                            Log.e("TAG", "sendEmailVerification", task.getException());
//                        }
//                    }
//                });
//    }
//
//    private void updateUI() {
//        if (isRegistering) {
//            signUpBtn.setVisibility(View.VISIBLE);
//            signUpBtn.setEnabled(true);
//            confirmPasswordET.setVisibility(View.VISIBLE);
//            evacuatorStatus.setText("Регистрация для эвакуаторщиков");
//            evacuatorStatus.setTextSize(20);
//            signInBtn.setVisibility(View.GONE);
//            question.setVisibility(View.GONE);
//        } else {
//            signUpBtn.setVisibility(View.INVISIBLE);
//            signUpBtn.setEnabled(false);
//            confirmPasswordET.setVisibility(View.GONE);
//            evacuatorStatus.setText("Вход для эвакуаторщиков");
//            evacuatorStatus.setTextSize(23);
//            signInBtn.setVisibility(View.VISIBLE);
//            question.setVisibility(View.VISIBLE);
//        }
//    }
//}
//