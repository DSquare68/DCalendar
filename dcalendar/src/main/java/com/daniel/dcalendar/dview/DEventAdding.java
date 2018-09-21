package com.daniel.dcalendar.dview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daniel.dcalendar.R;

public class DEventAdding extends AppCompatActivity {

    public static Button startDateButton;
    public static Button endDateButton;
    static Button startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout container = new LinearLayout(getApplicationContext());
        setContentView(R.layout.activity_devent_adding);
        //setContentView(container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        /*TextView text = new TextView(getContext());
        text.setText("dsfajkdfsajdfkshajadhsfj");
        container.addView(text);
        container.setOrientation(LinearLayout.VERTICAL);

        startDateButton = new Button(getContext());
        startDateButton.setOnClickListener(DEventAddingLogic.setOnClickListener(true,getContext()));

        endDateButton = new Button(getContext());
        endDateButton.setOnClickListener(DEventAddingLogic.setOnClickListener(false,getContext()));

        container.addView(startDateButton);
        container.addView(endDateButton);*/
    }

}
