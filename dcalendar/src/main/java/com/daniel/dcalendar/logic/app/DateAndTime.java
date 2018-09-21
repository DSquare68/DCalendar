package com.daniel.dcalendar.logic.app;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateAndTime {

    public static Date toDate(String date, String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return  sdf.parse(date+" "+time,null);
    }

    public static long toLong(Date date){
        return  date.getTime();
    }

    public static long toLong(String date, String time){
        return  toDate(date,time).getTime();
    }
}
