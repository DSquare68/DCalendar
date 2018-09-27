package com.daniel.dcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daniel.dcalendar.dview.DEventsScroll;
import com.daniel.dcalendar.dview.DMonth;
import com.daniel.dcalendar.dview.DWeekNames;
import com.daniel.dcalendar.dview.TopBar;


public class DCalendar extends LinearLayout {


    public DCalendar(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setId(R.id.calendar);
        addView(new TopBar(getContext()), new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) getResources().getDimension(R.dimen.arrow_height)));
        addView(new DWeekNames(getContext()));
        addView(new DMonth(getContext()));
        addView(new DEventsScroll(getContext()));
    }

    public DCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopBar getTopBar(){
        return (TopBar) getChildAt(0);
    }
    public DWeekNames getWeekNames(){
        return (DWeekNames) getChildAt(1);
    }
    public DMonth getDMonth(){
        return (DMonth) getChildAt(2);
    }
    public DEventsScroll getDEventScroll(){
        return (DEventsScroll) getChildAt(3);
    }

}
