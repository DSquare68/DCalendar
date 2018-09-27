package com.daniel.dcalendar.dview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daniel.dcalendar.logic.view.DEventScrollLogic;

public class DEventsScroll extends ScrollView {
    static com.daniel.dcalendar.event.DEvent[] events;

    static LinearLayout container;
    public DEventsScroll(Context context) {
        super(context);
        container=new LinearLayout(getContext());
        this.addView(container);
        setEvents(events);
        refrashEvents(getContext());

    }


    public static void setEvents(com.daniel.dcalendar.event.DEvent[] event) {
        events = event;
    }


    public static com.daniel.dcalendar.event.DEvent[] getEvents() {
        return events;
    }
    public static void refrashEvents(Context context){
        if(events==null) return;
        for(int i=0;i<events.length;i++){
            container.addView( new DEvent(context,events[i]));
        }
    }

}
