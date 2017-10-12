package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * The facade to the Model. Using Singleton design pattern to allow access to the model from
 * each controller
 */

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Model {
    /**Singleton Instance**/
    private static final Model _instance = new Model();

    /**
     * Gets the singleton instance of model
     * @return The singleton instance of model
     */
    public static Model get_instance() {
        return _instance;
    }
    private List<User> _users;
    private Map<Integer, RatReport> _reports;

    /**
     * Make a new Model
     */
    private  Model() {
        _users = new LinkedList<>();
        _reports = new HashMap<>();
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

        if(_users.contains(newUser)) {
            return false;
        } else {
            _users.add(newUser);
            return true;
        }
    }

    /**
     * Adds new rat reports to the Model
     * @param report New report to add to the app
     */
    public void add_report(RatReport report) {
        _reports.put(report.get_key(), report);
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
    public RatReport[] get_reports() {
        return (RatReport[]) _reports.values().toArray();
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
