package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.dcalendar.R;
import java.util.Date;

public class TopBar extends LinearLayout {

    ImageView leftArrow, rightArrow;
    TextView nameOfMonth;
    public TopBar(Context context) {
        super(context);
        leftArrow = new ImageView(getContext());
        leftArrow.setBackground(getContext().getDrawable(R.drawable.left_arrow));

        rightArrow = new ImageView(getContext());
        leftArrow.setBackground(getContext().getDrawable(R.drawable.right_arrow));

        Date today = new Date();
        String[] monthNames = context.getResources().getStringArray(R.array.month);
        nameOfMonth = new TextView(getContext());
        nameOfMonth.setText(monthNames[today.getMonth()]+" "+ String.valueOf(today.getYear()+1900));

        addView(leftArrow);
        addView(nameOfMonth);
        addView(rightArrow);
    }

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
