package com.example.fixcar20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegLoginActivity extends AppCompatActivity {

    TextView customerStatus, question, accauntCreateCustomer;
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET, confirmPasswordET;

    FirebaseAuth mAuth;
    DatabaseReference CustomerDatabaseRef;
    String OnlineCustomerID;

    ProgressDialog loadingBar;

    boolean isRegistering = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_login);

        customerStatus = findViewById(R.id.statusCustomer);
        question = findViewById(R.id.accauntCreateCustomer);
        accauntCreateCustomer = findViewById(R.id.accauntCreateCustomer);
        signInBtn = findViewById(R.id.signInCustomer);
        signUpBtn = findViewById(R.id.signUpCustomer);
        emailET = findViewById(R.id.customerEmail);
        passwordET = findViewById(R.id.customerPassword);
        confirmPasswordET = findViewById(R.id.customerConfirmPassword);

        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

        signUpBtn.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(false);
        confirmPasswordET.setVisibility(View.INVISIBLE);

        accauntCreateCustomer.setVisibility(View.VISIBLE);

        accauntCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRegistering) {
                    signInBtn.setVisibility(View.INVISIBLE);
                    accauntCreateCustomer.setVisibility(View.INVISIBLE);
                    signUpBtn.setVisibility(View.VISIBLE);
                    signUpBtn.setEnabled(true);
                    customerStatus.setText("Регистрация для клиентов");
                    customerStatus.setTextSize(20);
                    confirmPasswordET.setVisibility(View.VISIBLE);
                    isRegistering = true;
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                if (password.equals(confirmPassword)) {
                    RegisterCustomer(email, password);
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInCustomer(email, password);
            }
        });

    }

    private void SignInCustomer(String email, String password) {
        loadingBar.setTitle("Вход для клиентов");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null && user.isEmailVerified()) {
                        Toast.makeText(CustomerRegLoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent customerIntent = new Intent(CustomerRegLoginActivity.this, CustomersMapsActivity.class);
                        startActivity(customerIntent);
                    } else {
                        Toast.makeText(CustomerRegLoginActivity.this, "Ваша учетная запись не подтверждена или прошлая попытка регистрации не завершилась", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Произошла ошибка, попробуйте снова", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterCustomer(String email, String password) {
        loadingBar.setTitle("Регистрация клиента");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        OnlineCustomerID = user.getUid();
                        CustomerDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child("Customers").child(OnlineCustomerID);
                        CustomerDatabaseRef.setValue(true);

                        // Отправляем подтверждение на электронную почту
                        sendEmailVerification(user);

                        Toast.makeText(CustomerRegLoginActivity.this, "На вашу электронную почту отправлено подтверждение", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "Email sent.");
                        } else {
                            Log.e("TAG", "sendEmailVerification", task.getException());
                        }

                    }
                });
    }
}