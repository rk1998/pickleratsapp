package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * Created by rokrishnan on 9/27/17.
 */

public class User {
    private String _name;
    private String _userID;
    private String _passWord;
    private  boolean _isAdmin;
    public User(String name, String userID, String passWord, boolean isAdmin) {
        _name = name;
        _userID = userID;
        _passWord = passWord;
        _isAdmin = isAdmin;
    }
}
