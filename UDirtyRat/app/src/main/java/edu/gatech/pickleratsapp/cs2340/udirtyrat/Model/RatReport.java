package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class representing a RatReport in the app
 */

public class RatReport {
    /**Enum for the location type of the report**/
    public static List<String> locationTypes = Arrays.asList("Park", "Residential", "Business", "NYC Metro");

    public enum LocationType {
        PARK("Park"),
        RESIDENTIAL("Residential"),
        BUSINESS("Business"),
        METRO("NYC Metro");

        private final String _locationType;
        LocationType(String locationType) {
            _locationType = locationType;
        }

        public String get_locationType() {
            return _locationType;
        }
    }
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
    private Calendar _date;
    private String _date_string;
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
        _locationType = locationType;
        _zip = zip;
        _address = address;
        _city = city;
        _borough = borough;
        _latitude = latitude;
        _longitude = longitude;

        String[] dateArr = date.split("/");
        int dayNum = Integer.parseInt(dateArr[0]);
        int monthNum = Integer.parseInt(dateArr[1]);
        int yearNum = Integer.parseInt(dateArr[2].substring(0,4));
        _date = new GregorianCalendar(yearNum, monthNum - 1, dayNum);
        _date_string = date;

    }

    /**
     * Gets the key of the rat report
     * @return rat report key
     */
    public int get_key() {
        return _key;
    }

    /**
     *
     * @return date the report was made
     */
    public Calendar get_date() { return _date; }

    /**
     *
     * @return date the report was made as a string.
     */
    public String get_date_string() {
        return _date_string;
    }
    /**
     *
     * @return zip code of the report
     */
    public int get_zip() {
        return _zip;
    }

    /**
     *
     * @return the location type of this report
     */
    public String get_locationType() {
        return _locationType;
    }

    /**
     *
     * @return the address of the report
     */
    public String get_address() {
        return _address;
    }

    /**
     *
     * @return the city where the report was made
     */
    public String get_city() {
        return _city;
    }

    /**
     *
     * @return the borough where the report was made
     */
    public String get_borough() {
        return _borough;
    }

    /**
     *
     * @return the latitude of the rat sighting
     */
    public double get_latitude() {
        return _latitude;
    }

    /**
     *
     * @return the longitude of the rat sighting
     */
    public double get_longitude() {
        return _longitude;
    }

    @Override
    public String toString() {
        return " "+ _key + " : " + _address + " " + _city + " " + _date_string + "  ";
    }
}
