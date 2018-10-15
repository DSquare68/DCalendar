package com.daniel.dcalendar.dview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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


    public static void setEvents(com.daniel.dcalendar.event.DEvent[] event) {
        events = event;
    }


    public static com.daniel.dcalendar.event.DEvent[] getEvents() {
        return events;
    }
    public static void refreshEvents(Context context){
        if(events==null) return;
        for(int i=0;i<events.length;i++){
            container.addView( new DEvent(context,events[i]));
        }
    }

}
