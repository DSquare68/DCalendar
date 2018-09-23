package com.daniel.dcalendar.logic.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.daniel.dcalendar.dview.DEventAdding;
import com.daniel.dcalendar.event.DEvent;
import com.daniel.dcalendar.event.DEventDatabase;
import com.daniel.dcalendar.logic.app.DateAndTime;

import java.sql.Time;
import java.util.Date;

public class DEventAddingLogic {
    public static int date;
    public static int month;
    public static int year;
    static Date startDate= new Date(Long.MIN_VALUE), endDate=new Date(Long.MAX_VALUE), copyDate;
    static Date startTime = new Date(Long.MIN_VALUE), endTime = new Date(Long.MAX_VALUE), copyTime;


    public static View.OnClickListener setOnClickListener(final boolean start, final Context context){
        final Date today = new Date();
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(context, setListener(start),today.getYear()+1900,today.getMonth()+1,today.getDate());
                datePicker.show();
            }
        };
    }
    private static DatePickerDialog.OnDateSetListener setListener(final boolean start){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(start){
                    copyDate = startDate;
                    startDate= new Date(year-1900,month-1,dayOfMonth);
                    if(startDate.after(endDate)){
                        startDate=copyDate;
                    } else {
                        DEventAdding.startDateButton.setText(dayOfMonth + "." + month + "." + year);
                    }
                } else {
                    copyDate=startDate;
                    endDate= new Date(year-1900,month-1,dayOfMonth);
                    if (endDate.before(startDate)){
                        endDate=copyDate;
                    } else {
                        DEventAdding.endDateButton.setText(dayOfMonth + "." + month + "." + year);
                    }
                }
            }
        };
    }

    public static void addEvent(Context context) {
        DEventDatabase edb = new DEventDatabase(context);
        String name = DEventAdding.name.getText().toString();
        name = name=="" ? "" /*TODO empty name*/ : name;
        String localization=DEventAdding.localization.getText().toString();
        localization = localization=="" ? "" : localization;
        String description= DEventAdding.description.getText().toString();
        description = description=="" ? "" :description;
        String date =DEventAdding.startDateButton.getText().toString();
        date = date=="" ? DEventAdding.startDateButton.getHint().toString() : date;
        String time =DEventAdding.startTime.getText().toString();
        time = time=="" ? DEventAdding.startTime.getHint().toString(): time;
        long start=DateAndTime.toLong(date,time);
        date =DEventAdding.endDateButton.getText().toString();
        date = date=="" ? DEventAdding.endDateButton.getHint().toString() : date;
        time =DEventAdding.endTime.getText().toString();
        time = time=="" ? DEventAdding.endTime.getHint().toString() : time;
        long end=DateAndTime.toLong(date,time);
        long reminder=0; //TODO reminder set time
        DEvent event = new DEvent(name,localization,description, start,end,reminder);
        edb.add(event);
    }

    public static View.OnClickListener setOnClickListenerTime(final boolean start,final Context context) {
        final Date today = new Date();
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog datePicker = new TimePickerDialog(context, setListenerTime(start),today.getHours(),0,true);
                datePicker.show();
            }
        };
    }

    private static TimePickerDialog.OnTimeSetListener setListenerTime(final boolean start) {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
               if(start){
                   copyTime=startTime;
                   if(startDate.equals(new Date(Long.MIN_VALUE))){
                       startTime= new Date(copyTime.getYear(),copyTime.getMonth(),copyTime.getDate(),hourOfDay,minute);
                   } else{
                       startTime= new Date(startDate.getYear(),startDate.getMonth(),startDate.getDate(),hourOfDay,minute);
                   }
                   if(startTime.after(endTime)){
                       startTime=copyTime;
                   } else{
                       DEventAdding.startTime.setText(hourOfDay+":"+minute);
                   }
               } else{
                   copyTime=endTime;
                   if(endDate.equals(new Date(Long.MAX_VALUE))){
                       endTime= new Date(copyTime.getYear(),copyTime.getMonth(),copyTime.getDate(),hourOfDay,minute);
                   } else{
                       endTime= new Date(endDate.getYear(),endTime.getMonth(),endTime.getDate(),hourOfDay,minute);
                   }
                   if(endTime.before(startTime)){
                       startTime=copyTime;
                   } else{
                       DEventAdding.endTime.setText(hourOfDay+":"+minute);
                   }
               }
            }
        };
    }
}
