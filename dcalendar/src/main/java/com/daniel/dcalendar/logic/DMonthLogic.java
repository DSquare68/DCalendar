package com.daniel.dcalendar.logic;


import java.util.Date;
public class DMonthLogic {

    public static int[] setWeekDays(int i){
        int[] result = new int[7];
        Date today = new Date();
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
                firstOfMonth = new Date(firstOfMonth.getYear(),firstOfMonth.getMonth(),firstOfMonth.getDate()+7*i-weekDayNumber-1);
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
}
