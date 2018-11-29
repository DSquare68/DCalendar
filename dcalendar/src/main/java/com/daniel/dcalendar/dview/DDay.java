package com.daniel.dcalendar.dview;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.logic.view.DDayLogic;
import com.daniel.dcalendar.logic.view.DWeekLogic;
import java.util.Date;


public class DDay extends android.support.v7.widget.AppCompatButton {

    /**
     * default customize of button - from xml file
     */
    private boolean setDefault;
    private boolean shadowText;
    private boolean isEvent;
    private int year;
    private int month;
    private int day;

    public DDay(Context context, int yearD, int monthD, int dayNumber, boolean setDefault) {
        super(context);
        setText(String.valueOf(dayNumber));
        this.setDefault=setDefault;
        this.year=yearD;
        this.month=monthD;
        this.day=dayNumber;
        this.isEvent=DDayLogic.isEvent(year,month,day,getContext());
        if(month==Global.month) shadowText=false; else shadowText=true;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight=1;
        int paddingLR = (int) getResources().getDimension(R.dimen.side_padding_dday);
        int padding = (int) (paddingLR*(context.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE ? 0.15 : 1.0));
        lp.setMargins(paddingLR,padding,paddingLR,padding);
        setLayoutParams(lp);
        setSelected(false);

        setDesign();

        setShadow();

        setListeners();
    }

    public void setListeners() {
        setOnClickListener(DDayLogic.setOnClickClickListener(year,month,day,this));
        setOnLongClickListener(DDayLogic.setOnLongClickListener(year,month,day,getContext()));
    }

    public void setDesign() {
        if(this.setDefault){
            if(this.isEvent ==true){
                setBackgroundResource(R.drawable.calendar_week_day_button_pressed);
            } else{
                setBackgroundResource(R.drawable.calendar_week_day_button_unpressed);
            }
        }
    }

    public DDay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        this.setMeasuredDimension(width, width);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean dateCorrect(int year, int month, int day) {
        if(this.year==year&&this.month==month&&this.day==day) return true; else return false;
    }

    public void setToday() {
        if(checkToday())
            setBackgroundResource(R.drawable.calendar_week_day_today);
        else
            setDesign();

    }

    public boolean checkToday(){
        return new Date().getYear()==this.year&&new Date().getMonth()==this.month&&new Date().getDate()==this.day;
    }

    public void updateShadow(){
        if(month==Global.month) shadowText=false; else shadowText=true;

    }
    public void setShadow() {
        if(shadowText)
            this.setTextColor(getResources().getColor(R.color.text_shadow));
        else
            this.setTextColor(getResources().getColor(R.color.black));
    }

    public void refreshEvent() {
        isEvent=DDayLogic.isEvent(year,month,day,getContext());
        setDesign();
    }

    public void wasSelected() {
        if(Global.selectedYear==year&&Global.selectedMonth==month&&Global.selectedDay==day){
            setSelected(true);
        }
    }
}
