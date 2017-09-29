package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.*;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);
        // Locate the button in activity_main.xml
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.registerConfirm);

        // Capture button clicks
        login.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start LoginActivity.class
                Intent myIntent = new Intent(HomeScreen.this,
                        edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.LoginActivity.class);
                startActivity(myIntent);
            }
        });

        register.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start RegisterActivity.class
                Intent myIntent = new Intent(HomeScreen.this,
                        edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.RegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
