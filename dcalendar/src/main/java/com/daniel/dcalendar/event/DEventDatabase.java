package com.daniel.dcalendar.event;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void add(DEvent event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Columns.TABLE_NAME,null,event.getContentValues());
    }
    public DEvent[] get(Date date){
        SQLiteDatabase db = getReadableDatabase();
        DEvent[] result = null;
        int[] ids  = getIDs(1536940000000L,db);
        if(ids==null) return result;
        String inClause = arrayToString(ids);
        Cursor c = db.rawQuery("select * from "+Columns.TABLE_NAME+" where "+Columns._ID+" in " + inClause,null);
        c.moveToFirst();
        result = new DEvent[c.getCount()];
        for(int i=0;i<c.getCount();i++,c.moveToNext()){
            result[i]= new DEvent(c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getInt(5),c.getInt(6));
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
        Cursor c = db.rawQuery("SELECT "+Columns._ID+" FROM "+Columns.TABLE_NAME+" WHERE "+time+" >= "+Columns.START_TIME+" AND "+time+" <= "+Columns.END_TIME,null);
        if(c.getCount()<=0) return result;
        result = new int[c.getCount()];
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++,c.moveToNext()){
            result[i]=c.getInt(0);
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
}
