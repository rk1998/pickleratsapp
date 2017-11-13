package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * Class representing a user in our system.
 */

public class User {
    private String _name;
    private final String _userID;
    private String _passWord;
    private final boolean _isAdmin;
    public User(String name, String userID, String passWord, boolean isAdmin) {
        _name = name;
        _userID = userID;
        _passWord = passWord;
        _isAdmin = isAdmin;
    }
    public User(String userID, String passWord) {
        this("BigBoi", userID, passWord, false);
    }

    /**
     *
     * @return the User's name
     */
    public String get_name() {
        return _name;
    }

    /**
     *
     * @return the User's password
     */
    public String get_passWord() {
        return _passWord;
    }

    /**
     *
     * @return the User's userID
     */
    public String get_userID() {
        return _userID;
    }

    /**
     *
     * @return The User's admin status
     */
    public boolean get_isAdmin() {
        return _isAdmin;
    }

    /**
     * Sets the User's name
     * @param name the new name
     */
    public void set_name(String name) {
        _name = name;
    }

    /**
     * Sets the User's password
     * @param _passWord the new password
     */
    public void set_passWord(String _passWord) {
        this._passWord = _passWord;
    }


    /**
     * Checks equality of two Users
     * @return if obj is equal to this user
     */
    @Override
    public boolean equals(Object obj) {
        //TODO: add own implementation of this
        return (obj instanceof User) && (this ==  obj
                || this.get_userID().equals(((User) obj).get_userID()));
    }

    /**
     *
     * @return String representation of the user
     */
    @Override
    public String toString() {
        return "User " + _name + "has userId : " + _userID;
    }
}
