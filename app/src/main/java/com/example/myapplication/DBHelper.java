package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "calendar_table";
    public static final String DATABASE_NAME = "calendar_db";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "CALENDAR_TYPE";
    public static final String COL_3 = "EVENT_TYPE";
    public static final String COL_4 = "EVENT_NAME";
    public static final String COL_5 = "EVENT_START_DATE";
    public static final String COL_6 = "EVENT_END_DATE";
    public static final String COL_7 = "EVENT_REPEAT";
    public static final String COL_8 = "EVENT_TIME";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CALENDAR_TYPE TEXT,EVENT_TYPE TEXT," +
                "EVENT_NAME TEXT, EVENT_START_DATE TEXT, EVENT_END_DATE TEXT, " +
                "EVENT_REPEAT TEXT, EVENT_TIME TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(CalendarRecord calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,calendar.getCalendar_type());
        contentValues.put(COL_3,calendar.getEvent_type());
        contentValues.put(COL_4,calendar.getEvent_name());
        contentValues.put(COL_5,calendar.getEvent_start_date());
        contentValues.put(COL_6,calendar.getEvent_end_date());
        contentValues.put(COL_7,calendar.getEvent_repeat());
        contentValues.put(COL_8,calendar.getEvent_time());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public SQLiteDatabase getSqlLiteDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db;
    }
    public ArrayList<CalendarRecord> getAllDataForDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_5 + " = " + "\"" + date + "\"",null);
        Log.d("query", "*****************getAllDataForDate: "+"select * from "+TABLE_NAME +" where EVENT_START_DATE = "+date);
        if(res.getCount() == 0) {
            // show message
            Log.d("***************asha", "viewAll: Data not found");
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        ArrayList<CalendarRecord> records = new ArrayList<CalendarRecord>();
        while (res.moveToNext()) {

            CalendarRecord calendarRecord = new CalendarRecord();
            calendarRecord.setId(Integer.parseInt(res.getString(0)));
            calendarRecord.setCalendar_type(res.getString(1));
            calendarRecord.setEvent_type(res.getString(2));
            calendarRecord.setEvent_name(res.getString(3));
            calendarRecord.setEvent_start_date(res.getString(4));
            calendarRecord.setEvent_end_date(res.getString(5));
            calendarRecord.setEvent_repeat(res.getString(6));
            records.add(calendarRecord);
        }

        // Show all data
        Log.d("***************asha", buffer.toString());

        return records;
    }

    public ArrayList<CalendarRecord> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        if(res.getCount() == 0) {
            // show message
            Log.d("***************asha", "viewAll: Data not found");
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        ArrayList<CalendarRecord> records = new ArrayList<CalendarRecord>();
        while (res.moveToNext()) {

            CalendarRecord calendarRecord = new CalendarRecord();
            calendarRecord.setId(Integer.parseInt(res.getString(0)));
            calendarRecord.setCalendar_type(res.getString(1));
            calendarRecord.setEvent_type(res.getString(2));
            calendarRecord.setEvent_name(res.getString(3));
            calendarRecord.setEvent_start_date(res.getString(4));
            calendarRecord.setEvent_end_date(res.getString(5));
            calendarRecord.setEvent_repeat(res.getString(6));
            records.add(calendarRecord);
        }

        // Show all data
        Log.d("***************asha", buffer.toString());

        return records;
    }
    public boolean updateData(CalendarRecord calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,calendar.getId());
        contentValues.put(COL_2,calendar.getCalendar_type());
        contentValues.put(COL_3,calendar.getEvent_type());
        contentValues.put(COL_4,calendar.getEvent_name());
        contentValues.put(COL_5,calendar.getEvent_start_date());
        contentValues.put(COL_6,calendar.getEvent_end_date());
        contentValues.put(COL_7,calendar.getEvent_repeat());
        contentValues.put(COL_8,calendar.getEvent_time());
        db.update(TABLE_NAME, contentValues, "_id = ?",new String[] { String.valueOf(calendar.getId()) });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?",new String[] {id});
    }

}
