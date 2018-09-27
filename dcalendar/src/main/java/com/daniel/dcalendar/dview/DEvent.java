package com.daniel.dcalendar.dview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.dcalendar.logic.app.DateAndTime;

public class DEvent extends LinearLayout {

    public DEvent(Context context, com.daniel.dcalendar.event.DEvent dEvent) {
        super(context);
        this.setOrientation(HORIZONTAL);
        LinearLayout time = new LinearLayout(getContext());
        time.setOrientation(LinearLayout.VERTICAL);

        TextView startTime = new TextView(getContext());
        startTime.setText(DateAndTime.toString(dEvent.getStartTime()));

        TextView endTime = new TextView(getContext());
        endTime.setText(DateAndTime.toString(dEvent.getStartTime()));

        time.addView(startTime);
        time.addView(endTime);
        this.addView(time);

        LinearLayout details = new LinearLayout(getContext());
        details.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(getContext());
        name.setText(dEvent.getName());

        TextView location = new TextView(getContext());
        location.setText(dEvent.getLocation());

        details.addView(name);
        details.addView(location);
        this.addView(details);
    }
}
