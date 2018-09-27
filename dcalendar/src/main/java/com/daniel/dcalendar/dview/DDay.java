package com.daniel.dcalendar.dview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.logic.view.DDayLogic;


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

    public DDay(Context context, int yearD, int monthD, int dayNumber, boolean setDefault, boolean isEvent) {
        super(context);
        setText(String.valueOf(dayNumber));
        this.isEvent=isEvent;
        this.setDefault=setDefault;
        this.year=yearD;
        this.month=monthD;
        this.day=dayNumber;
        if(month==Global.month) shadowText=false; else shadowText=true;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight=1;
        lp.setMargins((int) getResources().getDimension(R.dimen.side_padding_dday),(int) getResources().getDimension(R.dimen.side_padding_dday),(int) getResources().getDimension(R.dimen.side_padding_dday),(int) getResources().getDimension(R.dimen.side_padding_dday));
        setLayoutParams(lp);

        if(this.setDefault){
            if(this.isEvent ==true){
                setBackgroundResource(R.drawable.calendar_week_day_button_pressed);
            } else{
                setBackgroundResource(R.drawable.calendar_week_day_button_unpressed);
            }
        }

        if(shadowText){
            this.setTextColor(getResources().getColor(R.color.text_shadow));
        }

        setOnClickListener(DDayLogic.setOnClickClickListener(year,month,day,getContext()));
        setOnLongClickListener(DDayLogic.setOnLongClickListener(year,month,day,getContext()));
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
}
