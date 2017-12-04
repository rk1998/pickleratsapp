package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

/**
 * Activity for the Report List View
 */
public class ReportListView extends AppCompatActivity {
    //private ListView mRatListView;
    private List<RatReport> reports;

    /**
     * Creates the ReportPage
     * @param savedInstanceData data generated while the app is currently running
     */
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_report_list_view);
        ListView mRatListView = (ListView) findViewById(R.id.ratList);
        Model model = Model.get_instance();
        reports = model.get_reports();
        Collections.reverse(reports);
        LinkedList<String> keys = new LinkedList<>();
        Log.d("LATEST REPORT KEY", "" + Model.get_latest_report_key());
        for (int i = 0; i < 100; i++) {
            keys.add(reports.get(i).toString());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, keys);

        mRatListView.setAdapter(adapter);
        mRatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RatReport selectedReport = reports.get(position);
                Intent detailIntent = new Intent(ReportListView.this, ReportDetailActivity.class);
                detailIntent.putExtra("key",  selectedReport.get_key());
                startActivity(detailIntent);
            }
        });

    }
}
