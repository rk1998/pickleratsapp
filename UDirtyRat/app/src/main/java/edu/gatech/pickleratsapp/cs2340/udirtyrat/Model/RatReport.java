package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

/**
 * Class representing a RatReport in the app
 */

public class RatReport {
    /**Enum for the location type of the report**/
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
    public enum Borough {
        QUEENS("Queens"),
        MANHATTAN("Manhattan"),
        BRONX("Bronx"),
        BROOKLYN("Brooklyn");
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
    private LocationType _locationType;
    private String _address;
    private String _city;
    private Borough _borough;
    private double _latitude;
    private double _longitude;

    public RatReport(int key, String date, LocationType locationType, String address, String city,
                     Borough borough, double latitude, double longitude) {
        _key = key;
        _date = date;
        _locationType = locationType;
        _address = address;
        _city = city;
        _borough = borough;
        _latitude = latitude;
        _longitude = longitude;

    }
    public int get_key() {
        return _key;
    }
    public LocationType get_locationType() {
        return _locationType;
    }
    public String get_address() {
        return _address;
    }
    public String get_city() {
        return _city;
    }
    public Borough get_borough() {
        return _borough;
    }
    public double get_latitude() {
        return _latitude;
    }
    public double get_longitude() {
        return _longitude;
    }
}
