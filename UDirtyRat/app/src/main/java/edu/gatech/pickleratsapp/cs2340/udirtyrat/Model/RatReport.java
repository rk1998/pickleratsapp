package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing a RatReport in the app
 */

public class RatReport {
    /**Enum for the location type of the report**/
//    public enum LocationType {
//        PARK("Park"),
//        RESIDENTIAL("Residential"),
//        BUSINESS("Business"),
//        METRO("NYC Metro");
//
//        private final String _locationType;
//        LocationType(String locationType) {
//            _locationType = locationType;
//        }
//        public String get_locationType() {
//            return _locationType;
//        }
//    }
    /**Enum for Borough where the report took place**/

    public static List<String> boroughs = Arrays.asList("Queens", "Manhattan", "Bronx", "Brooklyn", "Staten Island");

    public enum Borough {
        QUEENS("Queens"),
        MANHATTAN("Manhattan"),
        BRONX("Bronx"),
        BROOKLYN("Brooklyn"),
        STATEN_ISLAND("Staten Island");
        private final String _borough;
        Borough(String borough) {
            _borough = borough;
        }
        public String get_borough() {
            return _borough;
        }

    }
    private int _key;
    private String _date;
    private String _locationType;
    private int _zip;
    private String _address;
    private String _city;
    private String _borough;
    private double _latitude;
    private double _longitude;

    public RatReport(int key, String date, String locationType, int zip, String address, String city,
                     String borough, double latitude, double longitude) {
        _key = key;
        _date = date;
        _locationType = locationType;
        _zip = zip;
        _address = address;
        _city = city;
        _borough = borough;
        _latitude = latitude;
        _longitude = longitude;

    }
    public int get_key() {
        return _key;
    }
    public int get_zip() {
        return _zip;
    }
    public String get_locationType() {
        return _locationType;
    }
    public String get_address() {
        return _address;
    }
    public String get_city() {
        return _city;
    }
    public String get_borough() {
        return _borough;
    }
    public double get_latitude() {
        return _latitude;
    }
    public double get_longitude() {
        return _longitude;
    }
}
