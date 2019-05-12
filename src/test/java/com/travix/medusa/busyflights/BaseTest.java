package com.travix.medusa.busyflights;

/**
 * Created by amir on 5/12/19.
 */
public class BaseTest {

    public static final String SERVICE_URL = "/flights/search";
    public static final String ORIGIN_PARAM = "origin";
    public static final String DESTINATION_PARAM = "destination";
    public static final String DEPARTURE_PARAM = "departureDate";
    public static final String RETURN_PARAM = "returnDate";
    public static final String NUMBER_OF_PASSENGERS_PARAM = "numberOfPassengers";
    public static final String SORT_PARAM = "sort";
    public static final String PROJECTION_PARAM = "projection";

    public static final String VALID_ORIGIN_VALUE = "AAA";
    public static final String VALID_DESTINATION_VALUE = "AAA";
    public static final String VALID_DEPARTURE_DATE = "2011-12-03T10:15:30";
    public static final String VALID_RETURN_DATE = "2011-12-09T11:20:30";
    public static final String VALID_NUMBER_OF_PASSENGERS = "1";
    public static final String VALID_SORT_VALUE = "fare,asc";
    public static final String SORT_BY_FARE_ASCENDING = "fare,asc";
    public static final String VALID_PROJECTION_VALUE = "noProjection";
    public static final String FARE_ROUND_TWO_DECIMAL_PROJECTION = "fareRoundTwoDecimal";


}
