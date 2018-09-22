package com.daniel.dcalendar.dview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.logic.view.DEventAddingLogic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DEventAdding extends AppCompatActivity {

    public static Button startDateButton;
    public static Button endDateButton;
    public static Button startTime, endTime;
    public static Button add;
    public static EditText name, localization, description;
    public static Spinner reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout container = new LinearLayout(getApplicationContext());
        setContentView(R.layout.activity_devent_adding);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        findViews();
        setListeners();
        //setHints();
    }

    private void setListeners() {
        startDateButton.setOnClickListener(DEventAddingLogic.setOnClickListener(true,this));
        endDateButton.setOnClickListener(DEventAddingLogic.setOnClickListener(false,this));

        startTime.setOnClickListener(DEventAddingLogic.setOnClickListenerTime(true,this));
        endTime.setOnClickListener(DEventAddingLogic.setOnClickListenerTime(false,this));
    }

    private void findViews() {
        name = findViewById(R.id.event_name_edit_text);

        startDateButton = findViewById(R.id.start_date_button);
        endDateButton = findViewById(R.id.end_date_button);
        startTime = findViewById(R.id.start_time_button);
        endTime = findViewById(R.id.end_time_button);

        localization = findViewById(R.id.location_edit_text);
        reminder = findViewById(R.id.reminder_spinner);

        description = findViewById(R.id.description_edit_text);

        add = findViewById(R.id.add);
    }

    private void setHints() {
        // TODO this works if adding to present day.
        SimpleDateFormat sDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:00");
        Date date = new Date();

        startDateButton.setHint(sDate.format(date));
        endDateButton.setHint(sDate.format(date));

        startTime.setHint(time.format(date));
        date.setHours(date.getHours()+1);
        endTime.setHint(time.format(date));
    }

    public void addEvent(View view){
        DEventAddingLogic.addEvent(getApplicationContext());
    }
}
