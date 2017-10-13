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
        String dateString = toReportDetails.get_date();
        date.setText("Date: " + dateString);
        locationType = (TextView) findViewById(R.id.locationTypeDetail);
        String locationTypeString = toReportDetails.get_locationType();
        locationType.setText("Location Type: " + locationTypeString);
        zip = (TextView) findViewById(R.id.zipcodeDetail);
        String zipString = "" + toReportDetails.get_zip();
        zip.setText("ZipCode: " + zipString);
        address = (TextView) findViewById(R.id.addressDetail);
        String addressString = toReportDetails.get_address();
        address.setText("Address: " + addressString);
        city = (TextView) findViewById(R.id.cityDetail);
        String cityString = toReportDetails.get_city();
        city.setText("City: " + cityString);
        latitude = (TextView) findViewById(R.id.latitudeDetail);
        String latitudeString = "" + toReportDetails.get_latitude();
        latitude.setText("Latitude: " + latitudeString);
        longitude = (TextView) findViewById(R.id.longitudeDetail);
        String longitudeString = "" + toReportDetails.get_longitude();
        longitude.setText("Longitude: " + longitudeString);
        borough = (TextView) findViewById(R.id.boroughDetail);
        String boroughString = toReportDetails.get_borough();
        borough.setText("Borough: " + boroughString);
    }

}
