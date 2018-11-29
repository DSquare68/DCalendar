package com.daniel.dcalendar;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daniel.dcalendar.dview.DEventsScroll;
import com.daniel.dcalendar.dview.DMonth;
import com.daniel.dcalendar.dview.DWeekNames;
import com.daniel.dcalendar.dview.TopBar;
import com.daniel.dcalendar.logic.view.DCalendarLogic;

import static android.content.Context.SENSOR_SERVICE;


public class DCalendar extends LinearLayout {

    public DCalendar(Context context) {
        super(context);
        setId(R.id.calendar);
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.main, null, false);

        this.addView(layout);
        DCalendarLogic.setToday(this);
    }

    private void addViews(DCalendar dCalendar, LinearLayout layout) {
        for(int i=0;i<layout.getChildCount();i++){
            View v = layout.getChildAt(0);
            dCalendar.addView(v);
        }
    }


    public DCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopBar getTopBar(){
        return (TopBar) findViewById(R.id.top_bar);
    }
    public DWeekNames getWeekNames(){
        return (DWeekNames) findViewById(R.id.week_names);
    }
    public DMonth getDMonth(){
        return (DMonth) findViewById(R.id.month);
    }
    public DEventsScroll getDEventScroll(){
        return (DEventsScroll) findViewById(R.id.devent_scroll);
    }
}
