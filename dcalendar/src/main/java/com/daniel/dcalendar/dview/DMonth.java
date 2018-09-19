package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.daniel.dcalendar.logic.DMonthLogic;

public class DMonth extends LinearLayout {

   private final static int NUMBER_OF_WEEKS=6;

    public DMonth(Context context) {
        super(context);
        setOrientation(VERTICAL);
        for(int i=0;i<NUMBER_OF_WEEKS;i++){
            addView(new DWeek(context, DMonthLogic.setWeekDays(i)));
        }

    }

    public DMonth(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
