package com.daniel.dcalendar.logic.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daniel.dcalendar.dview.DEvent;
import com.daniel.dcalendar.dview.DEventInScroll;

public class DEventScrollLogic {

    public static View.OnClickListener setOnClickListenerDEvent(final DEventInScroll dEventInScroll, final int idDEvent) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dEventInScroll.getContext(), DEvent.class);
                intent.putExtra("id",idDEvent);
                dEventInScroll.getContext().startActivity(intent);
            }
        };
    }
}
