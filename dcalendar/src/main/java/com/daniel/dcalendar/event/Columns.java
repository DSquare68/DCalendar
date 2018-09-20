package com.daniel.dcalendar.event;

import android.provider.BaseColumns;

public interface Columns extends BaseColumns {
    String DATA_BASE_NAME = "drug.db";
    int DATA_BASE_VERSION = 1;

    String TABLE_NAME="event";

    //Future sometime maybe before half life 3
//    String CALENDAR_TAG;
//    String USER;
//    String MAIL;
//    String ACCOUNT;

    String EVENT_NAME="event_name";
    String START_TIME="start_time";
    String END_TIME="end_time";
    String LOCATION="location";
    String DESCRIPTION="description";
    String REMIND_TIME="remind_time";

}
