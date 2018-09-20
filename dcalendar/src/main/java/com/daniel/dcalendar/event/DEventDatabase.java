package com.daniel.dcalendar.event;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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
                Columns.LOCATION + " INTEGER, "+
                Columns.DESCRIPTION+ " TEXT, "+
                Columns.REMIND_TIME+ " TEXT ); ";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Columns.TABLE_NAME);
        onCreate(db);
    }
}
