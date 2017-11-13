package edu.gatech.pickleratsapp.cs2340.udirtyrat.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;


/**
 * Class representing Database, Used in Model
 */

public class Database {
//    private static final Database dBase= new Database(UDirtyRatApplication.getAppContext());
//    public static Database get_instance() {
//        return dBase;
//    }
    private final DataBaseHelper dbHelper;
    public Database(Context context) {
        if(context == null) {
            System.out.println("temp");
        }
        dbHelper = new DataBaseHelper(context);
    }
    public boolean insertData(RatReport report) {
        return dbHelper.insertData(report.get_key(), report.get_date_string(),
                report.get_locationType(), report.get_zip(), report.get_address(),
                report.get_city(), report.get_borough(), report.get_longitude(),
                report.get_latitude());
    }


    public RatReport findRatReportByKey(int key) {
        return dbHelper.getRatReportByKey(key);
    }

    public List<RatReport> getReportList() {
        return dbHelper.getAllReports();
    }
}
