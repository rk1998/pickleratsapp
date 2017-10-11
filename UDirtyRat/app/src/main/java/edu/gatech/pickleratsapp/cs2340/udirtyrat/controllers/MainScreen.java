package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.*;

public class MainScreen extends AppCompatActivity {
    private Button logOut;
    private Button viewReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logOut = (Button) findViewById(R.id.back_to_home_button);
        viewReports = (Button) findViewById(R.id.view_reports_button);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainScreen.this, HomeScreen.class));
            }
        });

        viewReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, ReportListView.class));
            }
        });

    }

}

