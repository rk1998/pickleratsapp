package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DataBase_Name = "RatReport.db";
    public static final String Table_Name = "Report_Table";
    public static final String Col_1 = "Key";
    public static final String Col_2 = "Date";
    public static final String Col_3 = "Location";
    public static final String Col_4 = "Zip Code";
    public static final String Col_5 = "Address";
    public static final String Col_6 = "City";
    public static final String Col_7 = "Borough";
    public static final String Col_8 = "Longitude";
    public static final String Col_9 = "Latitude";


    public DataBaseHelper (Context context) {
        super(context, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, LOCATION TEXT, ZIP INTEGER," +
                " ADDRESS TEXT, CITY TEXT, BOROUGH TEXT, LONGITUDE INTEGER, LATITUDE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String Date, String Location, int ZipCode, String Address, String City, String Borough, int Longitude, int Latitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, Date);
        contentValues.put(Col_3, Location);
        contentValues.put(Col_4, ZipCode);
        contentValues.put(Col_5, Address);
        contentValues.put(Col_6, City);
        contentValues.put(Col_7, Borough);
        contentValues.put(Col_8, Longitude);
        contentValues.put(Col_9, Latitude);
        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }
}
