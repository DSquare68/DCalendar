package com.daniel.dcalendar.logic.view;

import com.daniel.dcalendar.data.Global;

public class DWeekLogic {


    public static int setYearToDDay(int[] daysNumber, int i, int weekN) {
        if(weekN==1||weekN==0){
            if(daysNumber[0]<14){
                return Global.year;
            } else if(daysNumber[i]>14&&Global.month==0){
                return Global.year-1;
            }
        } else if (weekN==4||weekN==5){
            if(daysNumber[0]>14){
                return Global.year;
            } else if(daysNumber[i]<14&&Global.month==11){
                return Global.year+1;
            }
        }
        return Global.year;
    }

    public static int setMonthToDDay(int[] daysNumber, int i,int weekN) {
        if(weekN==1||weekN==0){
            if(daysNumber[i]<14){
                return Global.month;
            } else if(daysNumber[i]>14){
                return Global.month-1;
            }
        } else if (weekN==4||weekN==5){
            if(daysNumber[i]>14){
                return Global.month;
            } else if(daysNumber[i]<14){
                return Global.month+1;
            }
        }
        return Global.month;
    }
}
