package edu.gatech.pickleratsapp.cs2340.udirtyrat;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;
//import java.util.List;


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

    //Nick Huang's Tests
    @Test
    public void testAddingReport() {
        testModel.add_report(new RatReport(21, "10/19/2007", "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0));
        assertEquals(1, testModel.get_reports().size());
    }

    @Test
    public void testConfirmAddress() {
        testModel.add_report(new RatReport(21, "10/19/2007", "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0));
        RatReport r1 = new RatReport(21, "10/19/2007", "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0);
        assertEquals(r1.get_address(), testModel.get_report(21).get_address());
    }
}
