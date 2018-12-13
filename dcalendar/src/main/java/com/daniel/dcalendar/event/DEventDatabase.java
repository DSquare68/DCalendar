package com.daniel.dcalendar.event;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.util.Log;

import com.daniel.dcalendar.logic.app.DateAndTime;

import java.util.Date;


public class DEventDatabase extends SQLiteOpenHelper {
    Context context;
    public DEventDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Columns.DATA_BASE_NAME, factory, Columns.DATA_BASE_VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);

    }
    public DEventDatabase(Context context){
        super(context, Columns.DATA_BASE_NAME, null, Columns.DATA_BASE_VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE IF NOT EXISTS " + Columns.TABLE_NAME + " ( "+Columns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Columns.EVENT_NAME + " TEXT, "+
                Columns.START_TIME + " INTEGER, "+
                Columns.END_TIME + " INTEGER, "+
                Columns.START_DATE + " INTEGER, "+
                Columns.END_DATE + " INTEGER, "+
                Columns.REPETITION + " INTEGER, "+
                Columns.LOCATION + " TEXT, "+
                Columns.DESCRIPTION+ " TEXT, "+
                Columns.REMIND_TIME+ " INTEGER ); ";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Columns.TABLE_NAME);
        onCreate(db);
    }

    public void add(DEventDB event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Columns.TABLE_NAME,null,event.getContentValues());
    }
    public DEvent[] get(Date date){
        SQLiteDatabase db = getReadableDatabase();
        DEvent[] result = null;
        int[] ids  = getIDs(date.getTime(),db);
        if(ids==null) return result;
        String inClause = arrayToString(ids);
        Cursor c = db.rawQuery("select "+Columns.D_EVENT+" from "+Columns.TABLE_NAME+" where "+Columns._ID+" in " + inClause,null);
        c.moveToFirst();
        result = new DEvent[c.getCount()];
        for(int i=0;i<c.getCount();i++,c.moveToNext()){
            result[i]= new DEvent(c.getString(0),c.getString(1),c.getString(2),c.getLong(3),c.getLong(4),c.getLong(5),c.getLong(6));
            result[i].setId(ids[i]);
        }
        return result;
    }

    private String arrayToString(int[] ids) {
        String result ="(";
        for (int i:
             ids) {
            result+=i+", ";
        }
        result=result.substring(0,result.length()-2);
        result+=")";
        return result;
    }

    public int[] getIDs(long time, SQLiteDatabase db){
        int[] result =null;
        Cursor c = db.rawQuery("SELECT "+Columns._ID+" FROM "+Columns.TABLE_NAME+" WHERE "+time+" >= "+Columns.START_DATE+" AND "+time+" <= "+Columns.END_DATE,null);
        int[] repeated = getRepeated(time,db);
        if(c.getCount()+(repeated==null ? 0 : repeated.length) <=0) return result;
        result = new int[c.getCount()+(repeated==null ? 0 : repeated.length)];
        c.moveToFirst();
        int k=0;
        for(int i=0;i<c.getCount();i++,c.moveToNext()){
            result[k++]=c.getInt(0);
        }
        for(int i=0;i<(repeated==null ? 0 : repeated.length);i++){
            result[k++]=repeated[i];
        }
        return result;
    }

    private int[] getRepeated(long time, SQLiteDatabase db) {
        int[] ids =null;
        Cursor c = db.rawQuery("SELECT "+Columns._ID+", "+Columns.START_DATE+", "+Columns.END_DATE+", "+Columns.REPETITION+" FROM "+Columns.TABLE_NAME+" WHERE "+Columns.REPETITION+" > 0",null);
        if(c.getCount()<=0) return ids;
        int k=0;
        ids= new int[c.getCount()];
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++,c.moveToNext()){
            if(c.getLong(3)==1||c.getLong(3)==2) {
                if ((time- c.getLong(1)>0)&&
                        (( (time - c.getLong(1))) % DateAndTime.repetitionToTime(c.getInt(3))==0
                        || ( (time - c.getLong(1))) % DateAndTime.repetitionToTime(c.getInt(3))+( (time - c.getLong(2))) % DateAndTime.repetitionToTime(c.getInt(3))==( (time - c.getLong(1))) % DateAndTime.repetitionToTime(1)+( (time - c.getLong(1))) % DateAndTime.repetitionToTime(1)-1
                        || ( (time - c.getLong(2))) % DateAndTime.repetitionToTime(c.getInt(3))==0))
                    ids[k++]=c.getInt(0);
            } else{
                Date cal1 = new Date();
                Date cal2 = new Date();
                Date cal3 = new Date();
                cal1.setTime(time);
                cal2.setTime(c.getLong(1));
                cal3.setTime(c.getLong(2));
                if(c.getLong(3)==3 && (cal1.getTime()-cal2.getTime())>0 && (Math.abs(cal1.getDate()-cal2.getDate())<=3&&cal1.getDay()==cal2.getDay()
                                                    || (((double) (cal1.getTime() - cal2.getTime()) % DateAndTime.repetitionToTime(1)+((double) (cal1.getTime() - cal3.getTime())) % DateAndTime.repetitionToTime(1)< Math.abs(cal2.getTime()-cal3.getTime()) % DateAndTime.repetitionToTime(1)  )
                                                    || Math.abs(cal1.getDate()-cal3.getDate())<=3&&cal1.getDay()==cal3.getDay())))
                    ids[k++]=c.getInt(0);
                else if(c.getLong(3)==4 && (cal1.getTime()-cal2.getTime())>0 && Math.abs(cal1.getDate()-cal2.getDate())<=3&&cal1.getDay()==cal2.getDay() && cal1.getMonth()==cal2.getMonth()
                                                    || ((double) (cal1.getTime() - cal2.getTime()) % DateAndTime.repetitionToTime(1)+((double) (cal1.getTime() - cal3.getTime())) % DateAndTime.repetitionToTime(1)< Math.abs(cal2.getTime()-cal3.getTime()) % DateAndTime.repetitionToTime(1)  ) && cal2.getMonth()==cal3.getMonth()
                                                    || Math.abs(cal1.getDate()-cal3.getDate())<=3&&cal1.getDay()==cal3.getDay()&& cal2.getMonth()==cal3.getMonth())
                    ids[k++]=c.getInt(0);
            }
        }
        if(k==0) return null;
        int[] result = new int[k];
        for(int i=0;i<k;i++){
            result[i]=ids[i];
        }
        return result;
    }

    public boolean isEvent(long time) {
        if(getIDs(time,getReadableDatabase())==null){
            return false;
        } else {
            return true;
        }
    }

    public DEvent getByID(int id) {
        if(id==-1) return null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select "+Columns.D_EVENT+" from "+Columns.TABLE_NAME+" where "+Columns._ID+" = "+id,null);
        if(c==null) return null;
        c.moveToFirst();
        DEvent result= new DEvent(c.getString(0),c.getString(1),c.getString(2),c.getLong(3),c.getLong(4),c.getLong(5),c.getLong(6));
        result.setId(id);
        return result;
    }
}
