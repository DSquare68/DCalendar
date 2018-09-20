package com.daniel.dcalendar.logic;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daniel.dcalendar.dview.DMonth;
import com.daniel.dcalendar.dview.TopBar;

import java.util.Date;

public class TopBarLogic {


    static int whichMonth =-1;

    public static void setListener(final Context context, ImageView left, ImageView right){
        if(whichMonth==-1) {
            whichMonth = (new Date()).getMonth();
        }
        final Date[] today = {new Date()}; //Why in array ???
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today[0] = new Date(today[0].getYear(), today[0].getMonth()-1, today[0].getDate(), today[0].getHours(), today[0].getMinutes(), today[0].getSeconds());
                if(whichMonth==0){
                    whichMonth=11;
                } else {
                    whichMonth--;
                }
                TopBar.setMonthText(today[0].getMonth(),today[0].getYear());
                DMonth.renameDays(today[0].getYear(),whichMonth);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(today[0].getDate()!=31)
                    today[0] = new Date(today[0].getYear(), today[0].getMonth()+1, today[0].getDate(), today[0].getHours(), today[0].getMinutes(), today[0].getSeconds());
                else today[0] = new Date(today[0].getYear(), today[0].getMonth()+1, today[0].getDate()-1, today[0].getHours(), today[0].getMinutes(), today[0].getSeconds());
                if(whichMonth==11){
                    whichMonth=0;
                } else{
                    whichMonth++;
                }
                TopBar.setMonthText(today[0].getMonth(),today[0].getYear());
                DMonth.renameDays(today[0].getYear(),whichMonth);
            }
        });
    }
}
