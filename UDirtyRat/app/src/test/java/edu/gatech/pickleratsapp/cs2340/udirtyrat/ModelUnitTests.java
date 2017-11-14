package edu.gatech.pickleratsapp.cs2340.udirtyrat;

import org.junit.Before;
import org.junit.Test;

//import java.util.ArrayList;
//import java.util.List;

import java.util.List;

import static org.junit.Assert.*;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;


/**
 * Unit tests
 */

public class ModelUnitTests {
    private Model testModel;
    @Before
    public void setUp() {
        User a = new User("Jamie", "1111", "password", true);
        testModel = Model.get_instance();
        testModel.add_user(a);
    }

    //Jamie Hannukainen's tests
    @Test
    public void testLoginExistsRightPassword() {
        assertTrue(testModel.login_user(new User("Jamie", "1111", "password", true)));
    }
    @Test
    public void testLoginExistsWrongPassword() {
        assertFalse(testModel.login_user(new User("Jamie", "1111", "passWord", true)));
    }
    @Test
    public void testLoginNotExist() {
        assertFalse(testModel.login_user(new User("Julian", "0000", "jameskun", false)));
    }

    //Rohith Krishnan's Tests
    @Test
    public void testAddUserNotInSystem() {
        assertTrue(testModel.add_user(new User("Rick", "rick@highlevelcomedy.com", "morty", false)));
    }
    @Test
    public void testAddExistingUserId() {
        assertFalse(testModel.add_user(new User("Jim", "1111", "password", true)));
    }
    @Test
    public void testAddMultipleUsers() {
        User user1 = new User("bb", "ayy@bb", "123", false);
        User user2 = new User("aa", "byy@aa", "2345", false);
        User user3 = new User("xys", "dddd", "12345", false);
        testModel.add_user(user1);
        testModel.add_user(user2);
        testModel.add_user(user3);
        List<User> users_list = testModel.get_users();
        assertEquals(8, users_list.size()); // dummy users are added to model when constructed
    }
}
