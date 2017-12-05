package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;

/**
 * activity to create a new rat report
 */
public class ReportActivity extends AppCompatActivity {
    //DataBaseHelper db;
    // UI references.
    private EditText date;
    private Spinner locationType;
    private EditText zip;
    private EditText address;
    private EditText city;
    private EditText latitude;
    private EditText longitude;
    private Spinner borough;

//    private Button report;
//    private Button cancelReport;
    private RatReport ratReport;

    private DataBaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        // Set up the register form.
        date = (EditText) findViewById(R.id.date);
        locationType = (Spinner) findViewById(R.id.locationType);
        zip = (EditText) findViewById(R.id.zip);
        address = (EditText) findViewById(R.id.address);
        Button report = (Button) findViewById(R.id.report);
        city = (EditText) findViewById(R.id.city);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.Longitude);
        borough = (Spinner) findViewById(R.id.borough);
        Button cancelReport = (Button) findViewById(R.id.cancelReport);
        //populateAutoComplete();
        mDbHelper = new DataBaseHelper(this);

        ArrayAdapter<String> boroughAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                RatReport.boroughs);
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borough.setAdapter(boroughAdapter);

        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                RatReport.locationTypes);
        locationTypeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        locationType.setAdapter(locationTypeAdapter);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attemptReport()){
                    Intent appIntent = new Intent(ReportActivity.this, NavigationActivity.class);
                    appIntent.putExtra("latest_report_key", ratReport.get_key());
                    startActivity(appIntent);
                }
            }
        });

        cancelReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start new HomeScreen.Class
                Intent myIntent = new Intent(ReportActivity.this, NavigationActivity.class);
                startActivity(myIntent);
            }
        });
//        db = new DataBaseHelper(this);
    }
        private boolean attemptReport() {
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

            // Check that the user entered a zip code
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

                // add report to the model with random keY
                int n = Model.get_latest_report_key() + 1;
                Model model = Model.get_instance();
                mDbHelper.insertData(n, dateZ, locationTypeZ, zipZ, addressZ,
                        cityZ, boroughZ, latitudeZ, longitudeZ);
                ratReport = new RatReport(n, dateZ, locationTypeZ, zipZ,
                        addressZ, cityZ, boroughZ, latitudeZ, longitudeZ);
                model.add_report(ratReport);
                // go to the main screen
                return true;
            }
        }
    }
