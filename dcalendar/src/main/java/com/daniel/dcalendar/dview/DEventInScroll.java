package com.daniel.dcalendar.dview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.logic.app.DateAndTime;
import com.daniel.dcalendar.logic.view.DEventScrollLogic;

public class DEventInScroll extends LinearLayout {

    TextView startTime,endTime, name, location;

    public DEventInScroll(Context context, com.daniel.dcalendar.event.DEvent dEvent) {
        super(context);
        this.setOrientation(HORIZONTAL);
        inflate(context, R.layout.content_single_devent,this);

        startTime =findViewById(R.id.start_time);
        endTime =findViewById(R.id.end_time);
        name=findViewById(R.id.name_of_event);
        location=findViewById(R.id.location_of_event);

        startTime.setText(DateAndTime.toString(dEvent.getStartTime()));
        endTime.setText(DateAndTime.toString(dEvent.getEndTime()));
        name.setText(dEvent.getName());
        location.setText(dEvent.getLocation());

        this.setOnClickListener(DEventScrollLogic.setOnClickListenerDEvent(this,dEvent.getId()));
    }
}
