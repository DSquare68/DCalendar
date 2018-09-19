package com.daniel.dcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daniel.dcalendar.dview.DMonth;
import com.daniel.dcalendar.dview.TopBar;


public class DCalendar extends LinearLayout {


    public DCalendar(Context context) {
        super(context);
        setOrientation(VERTICAL);
        addView(new TopBar(getContext()), new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) getResources().getDimension(R.dimen.arrow_height)));
        addView( new DMonth(getContext()));
    }

    public DCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
