package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

/**
 * Created by Bram on 10/30/2017.
 */

public class GraphView extends AppCompatActivity {
    private Model model;
    private String startYear = "09/04/2015";
    private String endYear = "09/05/2015";
    private RatReport latestRatReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);
        model = Model.get_instance();
        Context context = GraphView.this;
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePicker startDate = new DatePicker(edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.GraphView.this);
                final DatePicker endDate = new DatePicker(edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.GraphView.this);
                LinearLayout linearLayout = new LinearLayout(edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.GraphView.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(startDate);
                linearLayout.addView(endDate);
                alertDialog.setTitle("Choose a date range");

                ScrollView scroller = new ScrollView(edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.GraphView.this);
                scroller.addView(linearLayout);

                alertDialog.setView(scroller);
                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final int startDay = startDate.getDayOfMonth();
                                final int startMonth = startDate.getMonth();
                                Log.d("Start Month ", " " + startMonth);
                                final int startYear = startDate.getYear();
                                final int endDay = endDate.getDayOfMonth();
                                final int endMonth = endDate.getMonth();
                                final int endYear = endDate.getYear();
                                Log.d("Date Range", startYear + " - " + endYear);
                                RatReport report;
                                GregorianCalendar startDate = new GregorianCalendar(startYear,
                                        startMonth, startDay);
                                GregorianCalendar endDate = new GregorianCalendar(endYear,
                                        endMonth, endDay);
                                List<RatReport> recent_reports =
                                        model.get_reports_in_range(startDate, endDate);
                                Log.d("Reports in range", ": " + recent_reports.size());
                                if (recent_reports.size() == 0) {
                                    Toast.makeText(getApplicationContext(),
                                            "No reports found in this date range",
                                            Toast.LENGTH_SHORT).show();
                                    recent_reports = model.get_reports();
                                    for (int i = recent_reports.size() - 1;
                                         i > recent_reports.size() - 150; i--) {
                                        report = recent_reports.get(i);
                                        //getting each report TODO: add report to graph or something
                                    }
                                } else {
                                    for (int i = 0; i < recent_reports.size(); i++) {
                                        report = recent_reports.get(i);

                                    }
                                }
                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}