package com.daniel.dcalendar.logic;

import android.content.Context;
import android.content.Intent;

import com.daniel.dcalendar.dview.DEventAdding;

public class DDayLogic {
    public static void openDialog(Context context) {
        Intent intent = new Intent(context, DEventAdding.class);
        context.startActivity(intent);
    }
}
