package com.example.fixcar20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.fixcar20.databinding.ActivityMain2Binding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fixcar20.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                {
                    try
                    {
                        sleep(1500);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                       if(FirebaseAuth.getInstance().getUid() != null && FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){

                           Intent welcomeIntent1 = new Intent(MainActivity.this, Main_Menu.class);
                           startActivity(welcomeIntent1);
                           finish();


                       }
                       else {


                           Intent welcomeIntent = new Intent(MainActivity.this, CustomerRegLoginActivity.class);
                           startActivity(welcomeIntent);
                       }
                    }
                }
            }
        };
        thread.start();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}