package com.daniel.dcalendar.event;

import android.content.ContentValues;

public class DEvent implements  Columns{
    String name,location, description;
    long startTime,endTime,remindTime;

    public DEvent() {
    }

    public DEvent(String name, String location, String description, long startTime, long endTime, long remindTime) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remindTime = remindTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(EVENT_NAME,name);
        cv.put(START_TIME, startTime);
        cv.put(END_TIME,endTime);
        cv.put(LOCATION,location);
        cv.put(DESCRIPTION, description);
        cv.put(REMIND_TIME,remindTime);
        return  cv;
    }
}
