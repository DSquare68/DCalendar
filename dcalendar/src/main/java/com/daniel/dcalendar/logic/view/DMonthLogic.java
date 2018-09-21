package com.daniel.dcalendar.logic.view;


import java.util.Date;
public class DMonthLogic {
    static Date today = new Date();

    public static int[] setWeekDays(int i){
        Date firstOfMonth = new Date(today.getYear(),today.getMonth(),1);
        int weekDayNumber = firstOfMonth.getDay();

        switch (weekDayNumber){
            case 0:
                firstOfMonth = new Date(firstOfMonth.getYear(),firstOfMonth.getMonth(),firstOfMonth.getDate()+7*i-6);
                return fillWeek(firstOfMonth);
            case 1:
                firstOfMonth = new Date(firstOfMonth.getYear(),firstOfMonth.getMonth(),firstOfMonth.getDate()+7*i);
                return fillWeek(firstOfMonth);
            default:
                firstOfMonth = new Date(firstOfMonth.getYear(),firstOfMonth.getMonth(),firstOfMonth.getDate()+7*i-weekDayNumber+1);
                return fillWeek(firstOfMonth);
        }
    }

    private static int[] fillWeek(Date monday){
        int[] result = new int[7];
        for (int i=0; i<result.length;i++){
            result[i] = monday.getDate();
            monday = new Date(monday.getYear(),monday.getMonth(),monday.getDate()+1);
        }
        return result;
    }

    public static int[] setWeekDays(int year, int whichMonth, int i) {
        today = new Date(year,whichMonth,1);
        return setWeekDays(i);
    }
}
