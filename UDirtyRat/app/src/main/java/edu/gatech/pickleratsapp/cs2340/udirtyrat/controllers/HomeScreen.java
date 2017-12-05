package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.*;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.*;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;


import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Activity for the home screen of the app
 */
public class HomeScreen extends AppCompatActivity {
    //private DataBaseHelper mdbHelper;
    /**
     * Custom Csv loading task, loads csv in background thread when home screen is created
     */
    private class LoadCSVTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String ... str) {
            long lineNumber = 0;
            Model model = Model.get_instance();
            try {
                InputStream is = getResources().openRawResource(R.raw.rats);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                br.readLine(); //get rid of header line
                while ((line = br.readLine()) != null) {
                    String[] reportData = line.split(",");
                    int id = Integer.parseInt(reportData[0]);
                    int zip = 0;
                    if(!reportData[8].isEmpty() && !reportData[8].equals("N/A")) {
                        zip = Integer.parseInt(reportData[8]);
                    }
                    double latitude = 0.0;
                    double longitude = 0.0;
                    int length = reportData.length;
                    if (!(length < 53) &&
                            (!reportData[length - 3].isEmpty()
                                    || !reportData[length - 3].equals("Unspecified"))
                            && (!reportData[length - 4].isEmpty()
                            || !reportData[length - 4].equals("Unspecified"))) {
                        latitude = Double.parseDouble(reportData[reportData.length - 3]);
                        longitude = Double.parseDouble(reportData[reportData.length - 4]);
                    }
                    model.load_data_csv(new RatReport(id, reportData[1], reportData[7], zip,
                            reportData[9], reportData[16], reportData[23],latitude, longitude));
                    lineNumber++;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//           model.load_database(HomeScreen.this);
            DataBaseHelper mdbHelper = new DataBaseHelper(HomeScreen.this);
            List<RatReport> data_list = mdbHelper.getAllReports();
            List<User> user_list = mdbHelper.getAllUsers();
            if(data_list.size() != 0) {
                for(RatReport report : data_list) {
                    model.add_report(report);
                }
            }
            if(user_list.size() != 0) {
                model.load_users(user_list);
            }
            return lineNumber;
        }
        protected void onPostExecute(Long result) {
            System.out.println("File Loaded: " + result + " lines");
        }

    }

    AnimationDrawable ratAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);
        // Locate the button in activity_main.xml
        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.registerConfirm);
        Model model = Model.get_instance();
        if(model.numReports() == 0) {
            //loadRatData();
            new LoadCSVTask().execute("load");
            // launches Async task in background while the Home Screen is being created
        }

        //adds animation rat
        ImageView ratImage = (ImageView) findViewById(R.id.ratani);
        ratImage.setBackgroundResource(R.drawable.ratgif);
        ratAnimation = (AnimationDrawable) ratImage.getBackground();


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
                        edu.gatech.pickleratsapp.cs2340.udirtyrat.
                                controllers.RegisterActivity.class);
                startActivity(myIntent);
            }
        });

        ratAnimation.start();
    }


}
