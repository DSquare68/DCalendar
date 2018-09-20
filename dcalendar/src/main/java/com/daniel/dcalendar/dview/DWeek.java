package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class DWeek extends LinearLayout {

    private final static int NUMBER_OF_DAYS=7;
    DDay[] dDays = new DDay[7];
    public DWeek(Context context, int[] daysNumber) {
        super(context);
        setWeightSum(7);
        for(int i=0;i<NUMBER_OF_DAYS;i++){
            addView(new DDay(context,daysNumber[i], true,true));
            dDays[i]=(DDay) getChildAt(i);
        }
    }

    public DWeek(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void renameDays(int[] daysNumber) {
        for(int i=0;i<NUMBER_OF_DAYS;i++){
            dDays[i].setText(String.valueOf(daysNumber[i]));
        }
    }
}
