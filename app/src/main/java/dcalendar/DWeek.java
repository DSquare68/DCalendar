package dcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class DWeek extends LinearLayout {

    private final static int NUMBER_OF_DAYS=7;

    public DWeek(Context context) {
        super(context);
        for(int i=0;i<NUMBER_OF_DAYS;i++){
            addView(new DDay(context,true,true));
        }
    }

    public DWeek(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
