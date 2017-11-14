package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

/**
 * Activity for seeing a detailed rat report
 */
public class ReportDetailActivity extends AppCompatActivity {
    // UI references.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int key = this.getIntent().getExtras().getInt("key");
        RatReport toReportDetails = Model.get_instance().get_report(key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        TextView date = (TextView) findViewById(R.id.dateDetail);
        String dateString = "Date: " + toReportDetails.get_date_string();
        date.setText(dateString);
        TextView locationType = (TextView) findViewById(R.id.locationTypeDetail);
        String locationTypeString = "Location Type: "
                + toReportDetails.get_locationType();
        locationType.setText(locationTypeString);
        TextView zip = (TextView) findViewById(R.id.zipcodeDetail);
        String zipString = "ZipCode: " + toReportDetails.get_zip();
        zip.setText(zipString);
        TextView address = (TextView) findViewById(R.id.addressDetail);
        String addressString = "Address: " + toReportDetails.get_address();
        address.setText(addressString);
        TextView city = (TextView) findViewById(R.id.cityDetail);
        String cityString = "City: " + toReportDetails.get_city();
        city.setText(cityString);
        TextView latitude = (TextView) findViewById(R.id.latitudeDetail);
        String latitudeString = "Latitude: " + toReportDetails.get_latitude();
        latitude.setText(latitudeString);
        TextView longitude = (TextView) findViewById(R.id.longitudeDetail);
        String longitudeString = "Longitude: " + toReportDetails.get_longitude();
        longitude.setText(longitudeString);
        TextView borough = (TextView) findViewById(R.id.boroughDetail);
        String boroughString = "Borough: " + toReportDetails.get_borough();
        borough.setText(boroughString);
    }

}
