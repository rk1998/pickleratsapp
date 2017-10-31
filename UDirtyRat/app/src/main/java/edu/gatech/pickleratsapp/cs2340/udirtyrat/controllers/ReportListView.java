package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.DataBaseHelper;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

public class ReportListView extends AppCompatActivity {
    DataBaseHelper db = new DataBaseHelper(this);
    private ListView mRatListView;
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_report_list_view);
        mRatListView = (ListView) findViewById(R.id.ratList);
        Model model = Model.get_instance();
        final List<RatReport> reports = model.get_reports();
        LinkedList<String> keys = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            keys.add(reports.get(i).toString());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, keys);

        mRatListView.setAdapter(adapter);
        final Context context = this;
        mRatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RatReport selectedReport = reports.get(position);
                Intent detailIntent = new Intent(ReportListView.this, ReportDetailActivity.class);
                System.out.println(selectedReport.toString());
                detailIntent.putExtra("key",  selectedReport.get_key());
                startActivity(detailIntent);
            }
        });
        Cursor res = db.getAllData();

    }
}
