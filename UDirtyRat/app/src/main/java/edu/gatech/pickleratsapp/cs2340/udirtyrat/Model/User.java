package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * Class representing a user in our system.
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
    public User(String userID, String passWord) {
        this("BigBoi", userID, passWord, false);
    }

    public String get_name() {
        return _name;
    }
    public String get_passWord() {
        return _passWord;
    }
    public String get_userID() {
        return _userID;
    }
    public boolean get_isAdmin() {
        return _isAdmin;
    }
    public void set_name(String name) {
        _name = name;
    }

    public void set_passWord(String _passWord) {
        this._passWord = _passWord;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO: add own implementation of this
        return (this == (User) obj || this._userID ==  ((User) obj)._userID);
    }

    @Override
    public String toString() {
        return "User " + _name + "has userId : " + _userID;
    }
}
