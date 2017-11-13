package edu.gatech.pickleratsapp.cs2340.udirtyrat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;


/**
 * Created by Jamie on 11/13/2017.
 */

public class ModelUnitTests {
    Model testModel;
    @Before
    public void setUp() {
        User a = new User("Jamie", "1111", "password", true);
        testModel = Model.get_instance();
        testModel.add_user(a);
    }

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
}
