package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * The facade to the Model. Using Singleton design pattern to allow access to the model from
 * each controller
 */

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import java.util.LinkedList;
import java.util.List;

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
    /**
     * Make a new Model
     */
    private  Model() {
        _users = new LinkedList<>();
    }

    /**
     *
     * @return The List of Users
     */
    public List<User> get_users() {
        return _users;
    }
}
