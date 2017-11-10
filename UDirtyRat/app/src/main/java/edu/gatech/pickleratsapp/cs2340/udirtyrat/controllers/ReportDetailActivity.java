package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

/**
 * Activity for seeing a detailed rat report
 */
public class ReportDetailActivity extends AppCompatActivity {
    // UI references.
    private TextView date;
    private TextView locationType;
    private TextView zip;
    private TextView address;
    private TextView city;
    private TextView latitude;
    private TextView longitude;
    private TextView borough;
    //private int key = this.getIntent().getExtras().getInt("key");
    private RatReport toReportDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int key = this.getIntent().getExtras().getInt("key");
        toReportDetails = Model.get_instance().get_report(key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        date = (TextView) findViewById(R.id.dateDetail);
        String dateString = "Date: " + toReportDetails.get_date_string();
        date.setText(dateString);
        locationType = (TextView) findViewById(R.id.locationTypeDetail);
        String locationTypeString = "Location Type: " + toReportDetails.get_locationType();
        locationType.setText(locationTypeString);
        zip = (TextView) findViewById(R.id.zipcodeDetail);
        String zipString = "ZipCode: " + toReportDetails.get_zip();
        zip.setText(zipString);
        address = (TextView) findViewById(R.id.addressDetail);
        String addressString = "Address: " + toReportDetails.get_address();
        address.setText(addressString);
        city = (TextView) findViewById(R.id.cityDetail);
        String cityString = "City: " + toReportDetails.get_city();
        city.setText(cityString);
        latitude = (TextView) findViewById(R.id.latitudeDetail);
        String latitudeString = "Latitude: " + toReportDetails.get_latitude();
        latitude.setText(latitudeString);
        longitude = (TextView) findViewById(R.id.longitudeDetail);
        String longitudeString = "Longitude: " + toReportDetails.get_longitude();
        longitude.setText(longitudeString);
        borough = (TextView) findViewById(R.id.boroughDetail);
        String boroughString = "Borough: " + toReportDetails.get_borough();
        borough.setText(boroughString);
    }

}
