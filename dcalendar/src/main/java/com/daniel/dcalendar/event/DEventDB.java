package com.daniel.dcalendar.event;

import android.content.ContentValues;

public class DEventDB extends DEvent{
    private long startDate,endDate;

    public DEventDB(String name, String location, String description, long startTime, long endTime, long remindTime, long startDate, long endDate) {
        super(name, location, description, startTime, endTime, remindTime);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(EVENT_NAME,name);
        cv.put(START_TIME, startTime);
        cv.put(END_TIME,endTime);
        cv.put(LOCATION,location);
        cv.put(DESCRIPTION, description);
        cv.put(REMIND_TIME,remindTime);
        cv.put(START_DATE,startDate);
        cv.put(END_DATE,endDate);
        return  cv;
    }
}
