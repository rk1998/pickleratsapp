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
    private static final String DataBase_Name = "RatReport.db";
    private static final String Table_Name = "Report_Table";
    private static final String Col_1 = "Key";
    private static final String Col_2 = "Date";
    private static final String Col_3 = "Location";
    private static final String Col_4 = "Code";
    private static final String Col_5 = "Address";
    private static final String Col_6 = "City";
    private static final String Col_7 = "Borough";
    private static final String Col_8 = "Longitude";
    private static final String Col_9 = "Latitude";
    private static final String USR_TABLE = "User_Table";
    private static final String USR_NAME = "Name";
    private static final String USR_ID = "UserID";
    private static final String PASSWORD = "Password";
    private static final String ADMINFLAG = "AdminFlag";
    private static final String LOCKEDFLAG = "LockedFlag";
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
            + ADMINFLAG + " INTEGER, "
            + LOCKEDFLAG + " INTEGER"
            +");";

    public DataBaseHelper (Context context) {
        super(context, DataBase_Name, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USR_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Puts a rat report into the database from its component parts
     * @param key unique for each report
     * @param Date of sighting
     * @param Location of sighting
     * @param ZipCode of sighting
     * @param Address of sighting
     * @param City in which sighting is located
     * @param Borough near which the sighting happened
     * @param Longitude of sighting
     * @param Latitude of sighting
     *
     */
    public void insertData(int key, String Date, String Location,
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
        db.insert(Table_Name, null, contentValues);
        //return result < 0;
    }

    /**
     * Puts a user into the database
     * @param user user to add to the database
     * @return if the user was added to the database
     */
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
        if(user.get_isLocked()) {
            contentValues.put(DataBaseHelper.LOCKEDFLAG, 1);
        } else {
            contentValues.put(DataBaseHelper.LOCKEDFLAG, 0);
        }
        long result = db.insert(USR_TABLE, null, contentValues);
        return result < 0;
    }

    /**
     * Retrieves a Ratreport from the database by key
     * @param key key of the rat report to look for
     * @return the RatReport corresponding to that key
     */
    public RatReport getRatReportByKey(int key) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_Name + "WHERE " + Col_1 + " ='" + key;
        Cursor cursor = database.rawQuery(query, null);
        RatReport report = null;
        if(cursor != null) {
            cursor.moveToFirst();
            report = new RatReport(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getDouble(7), cursor.getDouble(8));
            cursor.close();

        }
        return report;

    }

    /**
     * Gets a User by its user id
     * @param usr_id a user's id
     * @return User with corresponding user id
     */
    public User getUserById(String usr_id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + USR_TABLE + " WHERE " + USR_ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{usr_id});
        User user = null;
        if(cursor != null) {
            cursor.moveToFirst();
            boolean isAdmin = false;
            if(cursor.getInt(3) == 1) {
                isAdmin = true;
            }
            user = new User(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), isAdmin, false);
            if(cursor.getInt(4) == 1) {
                user.set_isLocked(true);
            } else {
                user.set_isLocked(false);
            }
            cursor.close();
        }
        return user;
    }

    /**
     * Changes a user already in the database
     * @param u a user that is getting changed
     */
    public void changeUserAttributes(User u) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USR_NAME, u.get_name());
        cv.put(USR_ID, u.get_userID());
        cv.put(PASSWORD, u.get_passWord());
        cv.put(ADMINFLAG, u.get_isAdmin());
        cv.put(LOCKEDFLAG, u.get_isLocked());
        String searchString = USR_ID + "='" + u.get_userID() + "'";
        database.update(USR_TABLE, cv, searchString, null);
    }

    /**
     *
     * @return a list of all users in the database
     */
    public List<User> getAllUsers() {
        SQLiteDatabase database = this.getWritableDatabase();
        List<User> user_list = new LinkedList<>();
        Cursor res = database.rawQuery("select * from " + USR_TABLE, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            User user = new User(res.getString(0), res.getString(1),
                    res.getString(2), false, false);
            if(res.getInt(4) == 1) {
                user.set_isLocked(true);
            } else {
                user.set_isLocked(false);
            }
            user_list.add(user);
            res.moveToNext();
        }
        res.close();
        return user_list;
    }

    /**
     *
     * @return a list of all reports in the database
     */
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
         return db.rawQuery("select * from " + Table_Name, null);
    }

}
