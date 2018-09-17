package dcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.daniel.dcalendar.R;

public class DCalendar extends LinearLayout {

    String[] monthNames= new String[12];

    public DCalendar(Context context) {
        super(context);
        monthNames =context.getResources().getStringArray(R.array.month);
    }

    public DCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
