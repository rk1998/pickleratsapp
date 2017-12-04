package edu.gatech.pickleratsapp.cs2340.udirtyrat;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.ChartData;
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
        User a = new User("Jamie", "1111", "password", true, false);
        testModel = Model.get_instance();
        testModel.add_user(a);
    }

    //Jamie Hannukainen's tests
    @Test
    public void testLoginExistsRightPassword() {
        assertTrue(testModel.login_user(new User("Jamie", "1111", "password", true, false)));
    }
    @Test
    public void testLoginExistsWrongPassword() {
        assertFalse(testModel.login_user(new User("Jamie", "1111", "passWord", true, false)));
    }
    @Test
    public void testLoginNotExist() {
        assertFalse(testModel.login_user(new User("Julian", "0000", "jameskun", false, false)));
    }

    //Rohith Krishnan's Tests
    @Test
    public void testAddUserNotInSystem() {
        assertTrue(testModel.add_user(new User("Rick", "rick@highlevelcomedy.com",
                "morty", false, false)));
    }
    @Test
    public void testAddExistingUserId() {
        assertFalse(testModel.add_user(new User("Jim", "1111", "password", true, false)));
    }
    @Test
    public void testAddMultipleUsers() {
        User user1 = new User("bb", "ayy@bb", "123", false, false);
        User user2 = new User("aa", "byy@aa", "2345", false, false);
        User user3 = new User("xys", "dddd", "12345", false, false);
        testModel.add_user(user1);
        testModel.add_user(user2);
        testModel.add_user(user3);
        List<User> users_list = testModel.get_users();
        assertEquals(8, users_list.size()); // dummy users are added to model when constructed
    }

    //Nick Huang's Tests

    @Test
    public void testConfirmAddress() {
        testModel.add_report(new RatReport(21, "10/19/2007", "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0));
        RatReport r1 = new RatReport(21, "10/19/2007", "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0);
        assertEquals(r1.get_address(), testModel.get_report(21).get_address());
    }
    @Test
    public void testAddingReport() {
        testModel.add_report(new RatReport(21, "10/19/2007",
                "Johnson", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0));
        assertEquals(5, testModel.get_reports().size());
    }

    //Giuseppe Pantalone's Tests

    @Test
    public void testEmptyDateRange() {
        List<ChartData> empty = testModel.get_data_in_range(new GregorianCalendar(2017, 10, 16),
                new GregorianCalendar(2017, 10, 17));
        assertEquals(1, empty.size());
        assertEquals(0, empty.get(0).getY());
    }

    @Test
    public void testWorkingDateRange() {
        RatReport r1 = new RatReport(21, "10/19/2010",
                "Johnson", 1234, "1234 Bop Street", "Harb", "Jorge", 12.0, 12.0);
        RatReport r2 = new RatReport(22,
                "1/11/2011", "Jim", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0);
        RatReport r3 =  new RatReport(23,
                "1/11/2016", "Jim", 1234, "1234 Hop Street", "Harb", "Jorge", 12.0, 12.0);
        testModel.add_report(r1);
        testModel.add_report(r2);
        testModel.add_report(r3);
        List<ChartData> data = testModel.get_data_in_range(new GregorianCalendar(2010, 0, 1),
                new GregorianCalendar(2016, 1, 28));
        assertEquals(74, data.size());
        int total = 0;
        for(int i = 0; i < data.size(); i++) {
            total += data.get(i).getY();

        }
        assertEquals(2, total);
    }

    //Bram Tyler's Tests

    @Test
    public void testEmptyGetRatReportDateRange() {
        List<RatReport> empty = testModel.get_reports_in_range(new GregorianCalendar(2017, 11, 16),
                new GregorianCalendar(2017, 11, 17));
        assertEquals(0, empty.size());
    }

    @Test
    public void testWorkingGetRatReportDateRange() {
        List<RatReport> data = testModel.get_reports_in_range( new GregorianCalendar(2000, 0, 1),
                new GregorianCalendar(2017, 11, 28));
        assertEquals(4, data.size());
        assertEquals(21, data.get(0).get_key());
    }

    @Test
    public void testStartDateAfterEndDate() {
        List<RatReport> empty = testModel.get_reports_in_range( new GregorianCalendar(2017, 11, 16), new
                GregorianCalendar(2017, 1, 17));
        assertEquals(0, empty.size());
    }
}
