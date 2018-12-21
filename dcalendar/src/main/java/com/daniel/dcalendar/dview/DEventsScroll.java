package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.event.DEvent;
import com.daniel.dcalendar.logic.view.DDayLogic;

public class DEventsScroll extends ScrollView {
    static com.daniel.dcalendar.event.DEvent[] events;
    static TextView info;
    static Button add;
    static LinearLayout container;
    public DEventsScroll(Context context) {
        super(context);
        container=new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        this.addView(container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        setAddButton();
        refreshEvents(getContext());
    }

    public DEventsScroll(Context context,  @Nullable AttributeSet attrs){
        super(context,attrs);
        container=new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        this.addView(container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        setAddButton();
        refreshEvents(getContext());
    }

    private void setAddButton() {
        info = new TextView(getContext());
        info.setText(getResources().getString(R.string.no_events));
        info.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) info.getLayoutParams()).gravity=Gravity.CENTER | Gravity.CENTER_VERTICAL;

        add = new Button(this.getContext());
        add.setText(getResources().getString(R.string.add));
        add.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) add.getLayoutParams()).gravity=Gravity.CENTER | Gravity.CENTER_VERTICAL;
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.yearDEvent=Global.selectedYear;
                Global.monthDEvent=Global.selectedMonth;
                Global.dayDEvent=Global.selectedDay;
                DDayLogic.openDialog(getContext());
            }
        });
    }

    public static void setEvents(com.daniel.dcalendar.event.DEvent[] event) {
        events = event;
    }


    public static com.daniel.dcalendar.event.DEvent[] getEvents() {
        return events;
    }
    public static void refreshEvents(Context context){
        container.removeAllViews();
        if(Global.selectedDay>0){
            container.addView(info);
            container.addView(add);
        }
        if(events==null||events.length==0) {
            return;

        }
        container.removeAllViews();
        sort(events);
        for(int i=0;i<events.length;i++){
            container.addView( new DEventInScroll(context,events[i]));
        }
    }

    private static void sort(DEvent[] events) {
        int j;
        DEvent key;

        for (int i=1;i<events.length;i++){
            j=i;
            key=events[i];
            while (j>0 && events[j-1].getStartTime()>key.getStartTime()){
                events[j]=events[j-1];
                j--;
            }
            events[j]=key;
        }
    }
    public static void clear(){
        container.removeAllViews();
    }
}
