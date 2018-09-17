package com.daniel.dcalendar.dview;

import android.content.Context;
import android.util.AttributeSet;

import com.daniel.dcalendar.R;


public class DDay extends android.support.v7.widget.AppCompatButton {

    /**
     * default customize of button - from xml file
     */
    private boolean setDefault;
    private boolean isEvent;

    public DDay(Context context, boolean setDefault, boolean isEvent) {
        super(context);
        this.isEvent=isEvent;
        this.setDefault=setDefault;
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
}
