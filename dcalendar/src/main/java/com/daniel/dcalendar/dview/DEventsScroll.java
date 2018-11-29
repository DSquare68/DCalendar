package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.daniel.dcalendar.event.DEvent;

public class DEventsScroll extends ScrollView {
    static com.daniel.dcalendar.event.DEvent[] events;

    static LinearLayout container;
    public DEventsScroll(Context context) {
        super(context);
        container=new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        this.addView(container);
        refreshEvents(getContext());
    }

    public DEventsScroll(Context context,  @Nullable AttributeSet attrs){
        super(context,attrs);
        container=new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        this.addView(container);
        refreshEvents(getContext());
    }

    public static void setEvents(com.daniel.dcalendar.event.DEvent[] event) {
        events = event;
    }


    public static com.daniel.dcalendar.event.DEvent[] getEvents() {
        return events;
    }
    public static void refreshEvents(Context context){
        container.removeAllViews();
        if(events==null) return;
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
