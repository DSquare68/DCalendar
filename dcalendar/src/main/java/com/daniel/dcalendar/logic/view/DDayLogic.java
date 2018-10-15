package com.daniel.dcalendar.logic.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.dview.DEventAdding;
import com.daniel.dcalendar.dview.DEventsScroll;
import com.daniel.dcalendar.event.DEventDatabase;
import java.util.Date;

public class DDayLogic {
    public static void openDialog(Context context) {
        Intent intent = new Intent(context, DEventAdding.class);
        context.startActivity(intent);
    }

    public static View.OnLongClickListener setOnLongClickListener(final int year, final int month, final int day,final Context context) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Global.yearDEvent=year;
                Global.monthDEvent=month;
                Global.dayDEvent=day;
                DDayLogic.openDialog(context);
                return false;
            }
        };
    }

    public static View.OnClickListener setOnClickClickListener(final int year,final int month,final int day, final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEventDatabase ed = new DEventDatabase(context);
                DEventsScroll.setEvents(ed.get(new Date(year,month,day,0,0,0)));
                DEventsScroll.refreshEvents(context);
            }
        };
    }
    public static boolean isEvent(int year, int month, int day, Context context) {
        DEventDatabase ed = new DEventDatabase(context);
        return ed.isEvent((new Date(year,month,day)).getTime());
    }
}
