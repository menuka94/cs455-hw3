package cs455.hadoop.util;

public class DataFields {
    // FIPS code of the state
    public static final int STATE_CODE = 1;

    // FIPS code of the county within a state
    public static final int COUNTY_CODE = 2;

    // Unique number within the county identifying the site
    public static final int SITE_NUM = 3;

    // AQS code corresponding to the parameter measured
    public static final int PARAMETER_CODE = 4;

    // Parameter Occurrence Code for different instruments
    public static final int POC = 5;

    // Angular distance north of the equator in DD
    public static final int LATITUDE = 6;

    // Angular distance east of the prime meridian in DD
    public static final int LONGITUDE = 7;

    // Datum associated with the Latitude and Longitude
    public static final int DATUM = 8;

    // Name of AQS parameter measured by the monitor Calendar
    public static final int PARAMETER_NAME = 9;

    // Calendar date (Local Standard Time, YYYY-MM-DD)
    public static final int DATE_LOCAL = 10;

    // 24-hour clock time (Local Standard Time, HH:MM)
    public static final int TIME_LOCAL = 11;

    // Calendar date (Greenwich Mean Time, YYYY-MM-DD)
    public static final int DATE_GMT = 12;

    // 24-hour clock time (Greenwich Mean Time, HH:MM)
    public static final int TIME_GMT = 13;

    // Measured value in the units specified
    public static final int SAMPLE_MEASUREMENT = 14;

    // Unit of measure for the parameter
    public static final int UNITS_OF_MEASURE = 15;

    // Method Detection Limit
    public static final int MDL = 16;

    // Total uncertainty associated with a measurement
    public static final int UNCERTAINTY = 17;

    // Indicate why values are missing
    public static final int QUALIFIER = 18;

    // Indication for the type of method used for collection
    public static final int METHOD_TYPE = 19;

    // Internal system code indicating the method
    public static final int METHOD_CODE = 20;

    // Description of the method for collection
    public static final int METHOD_NAME = 21;

    // State where the monitoring site is located.
    public static final int STATE_NAME = 22;

    // County where the monitoring site is located.
    public static final int COUNTY_NAME = 23;

    // Date of Last Change
    public static final int DATE_OF_LAST_CHANGE = 24;
}
