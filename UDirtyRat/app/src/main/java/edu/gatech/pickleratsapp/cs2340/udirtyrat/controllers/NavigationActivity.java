package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragManager;
    private SupportMapFragment mapFrag;
    private Model model;
    private String startYear = "09/12/2015";
    private String endYear = "09/12/2016";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        model = Model.get_instance();
        LayoutInflater inflater = LayoutInflater.from(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Context context = navigationView.getContext();
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText startInput = new EditText(NavigationActivity.this);
                startInput.setHint("MM/DD/YYYY");
                final EditText endInput = new EditText(NavigationActivity.this);
                endInput.setHint("MM/DD/YYYY");
                LinearLayout linearLayout = new LinearLayout(NavigationActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(startInput);
                linearLayout.addView(endInput);
                alertDialog.setTitle("Choose a date range");

                alertDialog.setView(linearLayout);
                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startYear = startInput.getText().toString();
                                endYear = endInput.getText().toString();
                                Log.d("Date Range", startYear + " - " + endYear);
                                if((startYear.equals("") || endYear.equals(""))
                                        || (startYear.length() < 4 ) || (endYear.length() < 4)) {
                                    Toast.makeText(getApplicationContext(),
                                            "Invalid Year", Toast.LENGTH_SHORT).show();
                                    startYear = "2015";
                                    endYear = "2016";
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


        fragManager = getSupportFragmentManager();
        mapFrag = (SupportMapFragment) fragManager.findFragmentById(R.id.map);
        mapFrag.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                RatReport report;
                List<RatReport> recent_reports = model.get_reports_in_range(startYear, endYear);
                Log.d("Reports in range", ": " + recent_reports.size());
                if(recent_reports.size() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "No reports found in this date range", Toast.LENGTH_SHORT).show();
                    recent_reports = model.get_reports();
                    for(int i = recent_reports.size() - 1; i > recent_reports.size() - 50; i--) {
                        report = recent_reports.get(i);
                        LatLng location = new LatLng(report.get_longitude(), report.get_latitude());
                        googleMap.addMarker(
                                new MarkerOptions().position(location).title(report.toString()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                    }

                } else {
                    for(int i = recent_reports.size() - 1; i > recent_reports.size() - 50; i--) {
                        report = recent_reports.get(i);
                        LatLng location = new LatLng(report.get_longitude(),
                                report.get_latitude());
                        googleMap.addMarker(new MarkerOptions().position(location).title(report.toString()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (id == R.id.nav_create_report) {
            startActivity(new Intent(NavigationActivity.this, ReportActivity.class));
        } else if (id == R.id.nav_view_report_list) {
            startActivity(new Intent(NavigationActivity.this, ReportListView.class));

        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(NavigationActivity.this, HomeScreen.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
