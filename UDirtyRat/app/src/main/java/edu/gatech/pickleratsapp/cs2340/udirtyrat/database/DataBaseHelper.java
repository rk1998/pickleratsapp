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

/**
 * Class that Directly Interacts with SQL Database. Writes data to and Reads data from it.
 */
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
    public static final String USR_TABLE = "User_Table";
    public static final String USR_NAME = "Name";
    public static final String USR_ID = "UserID";
    public static final String PASSWORD = "Password";
    public static final String ADMINFLAG = "AdminFlag";
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

    private static final String USER_TABLE_CREATE = "create table " + USR_TABLE
            + "("
            + USR_NAME + " TEXT NOT NULL, "
            + USR_ID + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + ADMINFLAG + " INTEGER"
            +");";

    public DataBaseHelper (Context context) {
        super(context, DataBase_Name, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, LOCATION TEXT, ZIP INTEGER," +
//                " ADDRESS TEXT, CITY TEXT, BOROUGH TEXT, LONGITUDE INTEGER, LATITUDE INTEGER)");
        db.execSQL(DATABASE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USR_TABLE);
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
    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.USR_NAME, user.get_name());
        contentValues.put(DataBaseHelper.USR_ID, user.get_userID());
        contentValues.put(DataBaseHelper.PASSWORD, user.get_passWord());
        if(user.get_isAdmin()) {
            contentValues.put(DataBaseHelper.ADMINFLAG, 1);
        } else {
            contentValues.put(DataBaseHelper.ADMINFLAG, 0);
        }
        long result = db.insert(USR_TABLE, null, contentValues);
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

    public User getUserById(String usr_id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + USR_TABLE + "WHERE " + USR_ID + " ='" + usr_id;
        Cursor cursor = database.rawQuery(query, null);
        User user = null;
        if(cursor != null) {
            cursor.moveToFirst();
            user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), false);
            cursor.close();
        }
        return user;
    }
    public List<User> getAllUsers() {
        SQLiteDatabase database = this.getWritableDatabase();
        List<User> user_list = new LinkedList<>();
        Cursor res = database.rawQuery("select * from " + USR_TABLE, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            User user = new User(res.getString(0), res.getString(1), res.getString(2), false);
            user_list.add(user);
            res.moveToNext();
        }
        res.close();
        return user_list;
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
