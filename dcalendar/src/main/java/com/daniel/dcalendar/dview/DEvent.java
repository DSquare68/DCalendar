package com.daniel.dcalendar.dview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.event.DEventDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DEvent extends AppCompatActivity {

    com.daniel.dcalendar.event.DEvent dEvent;
    TextView startTime,endTime, name, location, description, repetition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devent);
        Bundle b = getIntent().getExtras();
        int id = -1; // or other values
        if(b != null)
            id = b.getInt("id");
        DEventDatabase ded = new DEventDatabase(getApplicationContext());
        dEvent= ded.getByID(id);

        initComponents();
        setValues();


    }

    private void initComponents() {
        startTime=findViewById(R.id.start_time_devent);
        endTime=findViewById(R.id.end_time_devent);
        name=findViewById(R.id.name_devent);
        location=findViewById(R.id.location_devent);
        description=findViewById(R.id.description_devent);
        repetition=findViewById(R.id.repetition_devent);
    }

    private void setValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        startTime.setText(sdf.format(new Date(dEvent.getStartTime())));
        endTime.setText(sdf.format(new Date(dEvent.getEndTime())));
        name.setText(dEvent.getName());
        location.setText(dEvent.getLocation());
        description.setText(dEvent.getDescription());
        String[] rep = getApplicationContext().getResources().getStringArray(R.array.repetition_values);
        repetition.setText(rep[(int)dEvent.getRepetition()]);
    }
}
