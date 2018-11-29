package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.dcalendar.R;
import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.logic.view.TopBarLogic;

import java.util.Date;

public class TopBar extends LinearLayout {

    ImageView leftArrow, rightArrow;
    static TextView nameOfMonth;
    static String[] monthNames ;

    public TopBar(Context context) {
        super(context);
        init();
    }

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setOrientation(HORIZONTAL);
        setWeightSum(5);
        monthNames = this.getContext().getResources().getStringArray(R.array.month);

        LayoutParams lpL = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lpL.weight=2;

        LayoutParams lpR = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lpR.weight=2;

        LayoutParams lpText = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lpText.weight=1;

        leftArrow = new ImageView(getContext());
        leftArrow.setBackgroundResource(R.drawable.left_arrow);


        rightArrow = new ImageView(getContext());
        rightArrow.setBackgroundResource(R.drawable.right_arrow);


        Date today = new Date();
        if(Global.month!=0) {
            today.setMonth(Global.month);
            today.setYear(Global.year);
        }
        nameOfMonth = new TextView(getContext());
        nameOfMonth.setText(monthNames[today.getMonth()]+" "+ String.valueOf(today.getYear()+1900));
        nameOfMonth.setGravity(Gravity.CENTER);

        addView(leftArrow,lpL);
        addView(nameOfMonth,lpText);
        addView(rightArrow,lpR);

        TopBarLogic.setListener(getContext(),leftArrow,rightArrow);
        Global.year=(new Date()).getYear();
        Global.month=(new Date()).getMonth();
    }
    public static void setMonthText(int month, int year) {
        nameOfMonth.setText(monthNames[month]+" "+ String.valueOf(year+1900));
    }
}
