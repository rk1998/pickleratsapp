package edu.gatech.pickleratsapp.cs2340.udirtyrat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.LinkedList;
import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.*;

public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DataBase_Name = "RatReport.db";
    public static final String Table_Name = "Report_Table";
    public static final String Col_1 = "Key";
    public static final String Col_2 = "Date";
    public static final String Col_3 = "Location";
    public static final String Col_4 = "Code";
    public static final String Col_5 = "Address";
    public static final String Col_6 = "City";
    public static final String Col_7 = "Borough";
    public static final String Col_8 = "Longitude";
    public static final String Col_9 = "Latitude";
    private static final String DATABASE_CREATE = "create table " + Table_Name
            + "("
            + Col_1 + " INTEGER, "
            + Col_2 + " TEXT NOT NULL, "
            + Col_3 + " TEXT NOT NULL, "
            + Col_4 + " INTEGER, "
            + Col_5 + " TEXT NOT NULL, "
            + Col_6 + " TEXT NOT NULL, "
            + Col_7 + " TEXT NOT NULL, "
            + Col_8 + " DOUBLE, "
            + Col_9 + " DOUBLE"
            + ");";



    public DataBaseHelper (Context context) {
        super(context, DataBase_Name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, LOCATION TEXT, ZIP INTEGER," +
//                " ADDRESS TEXT, CITY TEXT, BOROUGH TEXT, LONGITUDE INTEGER, LATITUDE INTEGER)");
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(int key, String Date, String Location,
                              int ZipCode, String Address,
                              String City, String Borough,
                              double Longitude, double Latitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.Col_1, key);
        contentValues.put(DataBaseHelper.Col_2, Date);
        contentValues.put(DataBaseHelper.Col_3, Location);
        contentValues.put(DataBaseHelper.Col_4, ZipCode);
        contentValues.put(DataBaseHelper.Col_5, Address);
        contentValues.put(DataBaseHelper.Col_6, City);
        contentValues.put(DataBaseHelper.Col_7, Borough);
        contentValues.put(DataBaseHelper.Col_8, Longitude);
        contentValues.put(DataBaseHelper.Col_9, Latitude);
        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public RatReport getRatReportByKey(int key) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_Name + "WHERE " + Col_1 + " ='" + key;
        Cursor cursor = database.rawQuery(query, null);
        RatReport report = null;
        if(cursor != null) {
            cursor.moveToFirst();
            report = new RatReport(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getDouble(7), cursor.getDouble(8));
            cursor.close();

        }
        return report;

    }

    public List<RatReport> getAllReports() {
        SQLiteDatabase database = this.getWritableDatabase();
        List<RatReport> report_list = new LinkedList<>();
        Cursor cursor = getAllData(database);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            RatReport report = new RatReport(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getDouble(7), cursor.getDouble(8));
            report_list.add(report);
            cursor.moveToNext();
        }
        cursor.close();
        return report_list;
    }

    private Cursor getAllData(SQLiteDatabase db) {
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }

}
