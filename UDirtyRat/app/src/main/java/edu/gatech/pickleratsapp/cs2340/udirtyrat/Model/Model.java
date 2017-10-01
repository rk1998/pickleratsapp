package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * The facade to the Model. Using Singleton design pattern to allow access to the model from
 * each controller
 */

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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

    public boolean add_user(User newUser) {

        if(_users.contains(newUser)) {
            return false;
        } else {
            _users.add(newUser);
            return true;
        }
    }

    public boolean login_user(User user) {
        if(_users.contains(user)) {
           User attemptedUser = _users.get(_users.indexOf(user));
            return attemptedUser.get_passWord().equals(user.get_passWord());
        } else {
            return false;
        }
    }
}
