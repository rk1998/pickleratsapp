package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

import android.widget.Toast;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

public class ReportListView extends AppCompatActivity {
    private ListView mRatListView;
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_report_list_view);
        mRatListView = (ListView) findViewById(R.id.ratList);
        Model model = Model.get_instance();
        RatReport[] reports = model.get_reports();
        LinkedList<String> keys = new LinkedList<>();
        for (RatReport report : reports) {
            keys.add("" + report.get_key());
        }
        String[] keysDisplay = (String[]) keys.toArray();
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, keysDisplay);

        mRatListView.setAdapter(adapter);
    }
}
