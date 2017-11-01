package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * The facade to the Model. Using Singleton design pattern to allow access to the model from
 * each controller
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.Database;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.UDirtyRatApplication;


public class Model {
    /**Singleton Instance**/
    private static final Model _instance = new Model();
    private static int latest_report_key = 0;
    private Database database;
    /**
     * Gets the singleton instance of model
     * @return The singleton instance of model
     */
    public static Model get_instance() {
        return _instance;
    }

    public static int get_latest_report_key() {
        return latest_report_key;
    }
    private List<User> _users;
    private Map<Integer, RatReport> _reports;
    private Map<String, Integer> _string_to_key;
    private List<RatReport> _report_list;

    /**
     * Make a new Model
     */
    private  Model() {
        _users = new LinkedList<>();
        _reports = new HashMap<>();
        _report_list = new LinkedList<>();
       loadDummyUsers();
    }

    /**
     *
     * @return The Set of Users
     */
    public List<User> get_users() {
        return _users;
    }

    /**
     * Populates app with dummy users
     */
    private void loadDummyUsers() {
        _users.add(new User("Jamie", "jhaunkainen@gatech.edu", "1234", false));
        _users.add(new User("Rohith", "rkrishnan42@gatech.edu", "1234", false));
        _users.add(new User("Nick", "nhuang@gatech.edu", "1234", false));
        _users.add(new User("ayy bb", "b@b", "bb", false));
    }

    public int numReports() {
        return _reports.size();
    }

    /**
     * Adds a new user to the model
     * @param newUser a new user to add to the model
     * @return if the user was successfully added
     */
    public boolean add_user(User newUser) {

        if (_users.contains(newUser)) {
            return false;
        } else {
            _users.add(newUser);
            return true;
        }
    }

    /**
     * Loads report data into model from the database
     */
    public void load_database(Context context) {
        database = new Database(context);
        List<RatReport> saved_reports = database.getReportList();
        Log.d("saved reports", "" + saved_reports);
        if(saved_reports.size() != 0) {
            for(RatReport report: saved_reports) {
                _reports.put(report.get_key(), report);
                _report_list.add(report);
                latest_report_key = report.get_key();
            }
        }
    }

    /**
     * Loads report data from csv when app first starts
     * @param report report to be loaded from csv
     */
    public void load_data_csv(RatReport report) {
        _reports.put(report.get_key(), report);
        _report_list.add(report);
        latest_report_key = report.get_key();
    }

    /**
     * Adds new rat reports to the Model
     * @param report New report to add to the app
     */
    public void add_report(RatReport report) {
        if(!database.insertData(report)) {
            System.out.println("OOPS DATABASE SUX BALLZ");
        }
        Log.d("list size",""+database.getReportList().size());
        _reports.put(report.get_key(), report);
        _report_list.add(report);
        latest_report_key = report.get_key();
    }

    /**
     * Gets a rat report based on the key id of the report
     * @param key the key of the report the user wants
     * @return the RatReport corresponding to key
     */
    public RatReport get_report(int key) {
        return _reports.get(key);
    }

    /**
     * Gets a rat report based on its string representation
     * @param report_string the string representation of a rat report
     * @return the full rat report 
     */
    public RatReport get_report_from_string(String report_string) {
        return _reports.get(_string_to_key.get(report_string));
    }

    /**
     * returns the whole of the RatReports as an array for the list view mainly
     * @return the rat reports as an array
     */
    public List<RatReport> get_reports() {
        return _report_list;
    }


    /**
     * Gets reports within the date rage
     * @param startDate
     * @param endDate
     * @return
     */
    public List<RatReport> get_reports_in_range(GregorianCalendar startDate,
                                                GregorianCalendar endDate) {
        List<RatReport> results = new LinkedList<>();
        for (RatReport report: _report_list) {
            String[] date = report.get_date_string().split("/");
            int monthNum = Integer.parseInt(date[0]);
            int dayNum = Integer.parseInt(date[1]);
            int yearNum = Integer.parseInt(date[2].substring(0,4));
            Calendar reportDate = new GregorianCalendar(yearNum, monthNum - 1, dayNum);
            if ((reportDate.after(startDate) || reportDate.compareTo(startDate) == 0)
                    && (reportDate.before(endDate) || reportDate.compareTo(endDate) == 0)) {
                results.add(report);
            }
        }
        return results;
    }

    /**
     * Creates a data set for number of rat reports within year range [startYear, endYear]
     * @param startDate
     * @param endDate
     * @return a list of ChartData objects, represents data set for year range
     */
    public List<ChartData> get_data_in_range(GregorianCalendar startDate, GregorianCalendar endDate) {
        List<ChartData> dataSet = new LinkedList<>();
        int startYear = startDate.get(Calendar.YEAR);
        int endYear = startDate.get(Calendar.YEAR);
        for(int i = startYear; i <= endYear; i++) {
            int numReports = 0;
            for(RatReport r: _report_list) {
                int year = r.get_date().get(Calendar.YEAR);
                if(year == i) {
                    numReports++;
                }
            }
            dataSet.add(new ChartData(i, numReports));
        }
        return dataSet;
    }
    /**
     *
     * @param user user to login
     * @return if the user was logged in or not
     */
    public boolean login_user(User user) {
        if(_users.contains(user)) {
           User attemptedUser = _users.get(_users.indexOf(user));
            return attemptedUser.get_passWord().equals(user.get_passWord());
        } else {
            return false;
        }
    }
}
