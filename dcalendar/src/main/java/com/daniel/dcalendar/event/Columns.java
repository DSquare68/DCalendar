package com.daniel.dcalendar.event;

import android.provider.BaseColumns;

public interface Columns extends BaseColumns {
    String DATA_BASE_NAME = "dcalendar.db";
    int DATA_BASE_VERSION = 1;

    String TABLE_NAME="event";

    //Future sometime maybe before half life 3
//    String CALENDAR_TAG;
//    String USER;
//    String MAIL;
//    String ACCOUNT;

    String EVENT_NAME="event_name";

    /**
     * time submitted by user has date and time of event
     */

    String START_TIME="start_time";
    /**
     * time submitted by user has date and time of event
     */
    String END_TIME="end_time";
    /**
     * time filled programmatically for faster event finding, by date.
     */
    String START_DATE="start_date";
    /**
     * time filled programmatically for faster event finding, by date.
     */
    String END_DATE="end_date";
    String LOCATION="location";
    String DESCRIPTION="description";
    String REMIND_TIME="remind_time";


    String D_EVENT=EVENT_NAME+", "+LOCATION+", "+DESCRIPTION+", "+START_TIME+", "+END_TIME+", "+REMIND_TIME;
}
