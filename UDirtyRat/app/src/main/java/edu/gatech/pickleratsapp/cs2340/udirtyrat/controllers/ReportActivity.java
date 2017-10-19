package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Random;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

/**
 * activity to create a new rat report
 */
public class ReportActivity extends AppCompatActivity {

    // UI references.
    private EditText date;
    private Spinner locationType;
    private EditText zip;
    private EditText address;
    private EditText city;
    private EditText latitude;
    private EditText longitude;
    private Spinner borough;
    private Button report;
    private Button cancelReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        // Set up the register form.
        date = (EditText) findViewById(R.id.date);
        locationType = (Spinner) findViewById(R.id.locationType);
        zip = (EditText) findViewById(R.id.zip);
        address = (EditText) findViewById(R.id.address);
        report = (Button) findViewById(R.id.report);
        city = (EditText) findViewById(R.id.city);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.Longitude);
        borough = (Spinner) findViewById(R.id.borough);
        cancelReport = (Button) findViewById(R.id.cancelReport);
        //populateAutoComplete();

        ArrayAdapter<String> boroughAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, RatReport.boroughs);
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borough.setAdapter(boroughAdapter);

        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, RatReport.locationTypes);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationType.setAdapter(locationTypeAdapter);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attemptReport()){
                    Intent appIntent = new Intent(ReportActivity.this, MainScreen.class);
                    startActivity(appIntent);
                }
            }
        });

        cancelReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start new HomeScreen.Class
                Intent myIntent = new Intent(ReportActivity.this, HomeScreen.class);
                startActivity(myIntent);
            }
        });
    }

        protected boolean attemptReport() {
            // Reset errors.
            date.setError(null);
            zip.setError(null);
            address.setError(null);
            city.setError(null);
            latitude.setError(null);
            longitude.setError(null);

            boolean cancel = false;
            View focusView = null;

            // Check for a valid date, if the user entered one.
            if (TextUtils.isEmpty(date.getText().toString())) {
                date.setError("This field is required");
                focusView = date;
                cancel = true;
            }

            // Check that the user entered a zipcode
            if (TextUtils.isEmpty(zip.getText().toString())) {
                zip.setError("This field is required");
                focusView = zip;
                cancel = true;
            }

            // Check that the user entered a city
            if (TextUtils.isEmpty(city.getText().toString())) {
                city.setError("This field is required");
                focusView = city;
                cancel = true;
            }

            // Check that the user entered an address
            if (TextUtils.isEmpty(address.getText().toString())) {
                address.setError("This field is required");
                focusView = address;
                cancel = true;
            }

            // Check that the user entered a latitude
            if (TextUtils.isEmpty(latitude.getText().toString())) {
                latitude.setError("This field is required");
                focusView = latitude;
                cancel = true;
            }

            // Check that the user entered a longitude
            if (TextUtils.isEmpty(longitude.getText().toString())) {
                longitude.setError("This field is required");
                focusView = longitude;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
                return false;
            } else {
                //get values
                String dateZ = date.getText().toString();
                String locationTypeZ = locationType.getSelectedItem().toString();
                int zipZ = Integer.parseInt(zip.getText().toString());
                String cityZ = city.getText().toString();
                String addressZ = address.getText().toString();
                double latitudeZ = Double.parseDouble(latitude.getText().toString());
                double longitudeZ = Double.parseDouble(longitude.getText().toString());
                String boroughZ = borough.getSelectedItem().toString();

                // add report to the model with random key
                Random rand = new Random();
                int n = Model.get_latest_report_key() + 1;
                Model model = Model.get_instance();
                model.add_report(new RatReport(n, dateZ, locationTypeZ, zipZ,
                        addressZ, cityZ, boroughZ, latitudeZ, longitudeZ));
                // go to the main screen
                return true;
            }
        }
    }
