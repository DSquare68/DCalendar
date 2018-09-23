package com.daniel.dcalendar.logic.app;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {

    public static Date toDate(String date, String time){
        date= date==null ? "01-01-1900" : date;
        //date.replace(".","-");
        time= time==null ? "00:00" : time;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.parse(date+" "+time,new ParsePosition(0));
    }

    public static long toLong(Date date){
        return  date.getTime();
    }

    public static long toLong(String date, String time){
        return  toDate(date,time).getTime();
    }
}
