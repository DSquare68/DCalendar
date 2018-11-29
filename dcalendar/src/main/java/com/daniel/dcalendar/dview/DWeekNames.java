package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.dcalendar.R;

public class DWeekNames extends LinearLayout {

    public DWeekNames(Context context) {
        super(context);
        this.setWeightSum(7);
        this.setOrientation(HORIZONTAL);
        init();
    }
    public DWeekNames(Context context,  @Nullable AttributeSet attrs){
        super(context,attrs);
        init();
    }
    private void init(){
        TextView day;
        LayoutParams dayP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dayP.weight=1;
        String[] dayNames = getResources().getStringArray(R.array.day_names);
        for(int i=0;i<dayNames.length;i++){
            day = new TextView(getContext());
            day.setGravity(Gravity.CENTER);
            day.setText(dayNames[i]);
            this.addView(day,i,dayP);
        }
    }
}
