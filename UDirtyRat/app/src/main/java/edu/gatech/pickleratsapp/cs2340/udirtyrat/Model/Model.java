package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;



import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;


/**
 * The facade to the Model. Using Singleton design pattern to allow access to the model from
 * each controller
 */
public class Model {
    /**Singleton Instance**/
    private static final Model _instance = new Model();
    private static int latest_report_key = 0;
    //private DataBaseHelper database;
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
    private final List<User> _users;
    private final Map<Integer, RatReport> _reports;
    private final List<RatReport> _report_list;

    /**
     * Make a new Model
     */
    private  Model() {
        _users = new LinkedList<>();
        _reports = new HashMap<>();
        _report_list = new LinkedList<>();
        //database = new DataBaseHelper(UDirtyRatApplication.getAppContext());
       //loadDummyUsers();
    }

    /**
     *  Load users from the database into a list
     * @param users users to put into model
     */
    public void load_users(List<User> users) {
        for(User u: users) {
            _users.add(u);
        }
    }

    /**
     * Changes the locked status of a user
     * @param locked_status new locked status
     * @param usr_id id of user to change the lock status of
     * @param context app context (used to create database helper object)
     */
    public void set_user_locked_status(boolean locked_status, String usr_id, Context context) {
        DataBaseHelper mDataBaseHelper = new DataBaseHelper(context);
        for(User u: _users) {
            if (u.get_userID().equals(usr_id)) {
                u.set_isLocked(locked_status);
                mDataBaseHelper.changeUserAttributes(u);
                return;
            }
        }

    }

    /**
     * Populates app with dummy users
     */
    private void loadDummyUsers() {
        _users.add(new User("Jamie", "jhaunkainen@gatech.edu", "1234", false, false));
        _users.add(new User("Rohith", "rkrishnan42@gatech.edu", "1234", false, false));
        _users.add(new User("Nick", "nhuang@gatech.edu", "1234", false, false));
        _users.add(new User("ayy bb", "b@b", "bb", false, false));
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
     * Returns the list of users
     * @return the list of users
     */
    public List<User> get_users() {
        return _users;
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
//        database.insertData(report.get_key(), report.get_date_string(),
//                report.get_locationType(), report.get_zip(), report.get_address(),
//                report.get_city(), report.get_borough(), report.get_longitude(),
//                report.get_latitude());
        if (report == null) {
            throw new IllegalArgumentException("cannot pass null report to model");
        }
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
     * returns the whole of the RatReports as an array for the list view mainly
     * @return the rat reports as an array
     */
    public List<RatReport> get_reports() {
        return _report_list;
    }


    /**
     * Gets reports within the date rage
     * @param startDate beginning of search range
     * @param endDate end of search range
     * @return all reports within the range
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
     * @param startDate beginning of search range
     * @param endDate end of search range
     * @return a list of ChartData objects, represents data set for year range
     */
    public List<ChartData> get_data_in_range(GregorianCalendar startDate,
                                             GregorianCalendar endDate) {
        List<ChartData> dataSet = new LinkedList<>();
        int startYear = startDate.get(Calendar.YEAR);
        int startMonth =  startDate.get(Calendar.MONTH);
        int endYear = endDate.get(Calendar.YEAR);
        int endMonth = endDate.get(Calendar.MONTH);
        int monthsSpanned;
        if (startYear == endYear) {
            monthsSpanned = endMonth - startMonth + 1;
        } else {
            monthsSpanned = ((endYear - startYear - 1) * 12) + (12 - startMonth) + (endMonth + 1);
        }
        int[] counts = new int[monthsSpanned];
        for (RatReport r : _report_list) {
            if ((r.get_date().after(startDate) || r.get_date().compareTo(startDate) == 0)
                && (r.get_date().before(endDate) || r.get_date().compareTo(endDate) == 0)) {
                int monthInRange;
                int reportYear = r.get_date().get(Calendar.YEAR);
                int reportMonth = r.get_date().get(Calendar.MONTH);
                if (reportYear == startYear) {
                    monthInRange = reportMonth - startMonth;
                } else {
                    monthInRange = ((reportYear - startYear - 1) * 12) + (12 - startMonth)
                        + reportMonth;
                }
                counts[monthInRange]++;
            }
        }
        for (int i = 0; i < monthsSpanned; i++) {
            dataSet.add(new ChartData(i, counts[i]));
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
            return attemptedUser.get_passWord().equals(user.get_passWord()) && !user.get_isLocked();
        } else {
            return false;
        }
    }
}
