package com.example.myapplication.academic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CalendarRecord;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewReminderAcademicActivity extends AppCompatActivity {

    private int mYear,mMonth,mDay;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme",0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reminder_academic);

        final Button pickDate = findViewById(R.id.pick_date);
        final TextView textView = findViewById(R.id.date);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(CalendarRecord.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(sdf.format(myCalendar.getTime()));
            }


        };

        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(AddNewReminderAcademicActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                textView.setText(new DecimalFormat("00").format(dayOfMonth) + "-"
                                        + new DecimalFormat("00").format(monthOfYear+1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        /*888888888888888888888888888888*/
        {
            final Button pickDate2 = findViewById(R.id.pick_date2);
            final TextView textView2 = findViewById(R.id.date2);

            final Calendar myCalendar2 = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar2.set(Calendar.YEAR, year);
                    myCalendar2.set(Calendar.MONTH, monthOfYear);
                    myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    // myCalendar.add(CalendarRecord.DATE, 0);
                    String myFormat = "yyyy-MM-dd"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    textView2.setText(sdf.format(myCalendar2.getTime()));
                }


            };

            pickDate2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(AddNewReminderAcademicActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // Display Selected date in textbox

                                    if (year < mYear)
                                        view.updateDate(mYear,mMonth,mDay);

                                    if (monthOfYear < mMonth && year == mYear)
                                        view.updateDate(mYear,mMonth,mDay);

                                    if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                        view.updateDate(mYear,mMonth,mDay);

                                    textView2.setText(new DecimalFormat("00").format(dayOfMonth) + "-"
                                            + new DecimalFormat("00").format(monthOfYear+1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                    dpd.show();

                }
            });
        }
        /*88888888888888888888888888888*/
        this.setTitle("");
    }

    public void addReminder(View view) {
        EditText subject = (EditText) findViewById(R.id.textView2);
        TextView start_date = (TextView) findViewById(R.id.date);
        TextView end_date = (TextView) findViewById(R.id.date2);
        CheckBox monday = (CheckBox)findViewById(R.id.monday);
        CheckBox tuesday = (CheckBox)findViewById(R.id.tuesday);
        CheckBox wednesday = (CheckBox)findViewById(R.id.wednesday);
        CheckBox thursday = (CheckBox)findViewById(R.id.thursday);
        CheckBox friday = (CheckBox)findViewById(R.id.friday);
        CheckBox saturday = (CheckBox)findViewById(R.id.saturday);
        String repeat_events = "";
        if(monday.isChecked()) repeat_events = repeat_events + "M";
        if(tuesday.isChecked()) repeat_events = repeat_events + "T";
        if(wednesday.isChecked()) repeat_events = repeat_events + "W";
        if(thursday.isChecked()) repeat_events = repeat_events + "R";
        if(friday.isChecked()) repeat_events = repeat_events + "F";
        if(saturday.isChecked()) repeat_events = repeat_events + "S";
        CalendarRecord calendarRecord = new CalendarRecord();
        DBHelper dbHelper = new DBHelper(this);
        calendarRecord.setCalendar_type("Academic");
        calendarRecord.setEvent_type("Reminder");
        calendarRecord.setEvent_name(subject.getText().toString());
        calendarRecord.setEvent_start_date(start_date.getText().toString());
        calendarRecord.setEvent_end_date(end_date.getText().toString());
        calendarRecord.setEvent_repeat(repeat_events);
        /*boolean isInserted = dbHelper.insertData(calendarRecord);
        if(isInserted == true)
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();*/
        boolean isInserted = true;
        Date sdate = null;
        Date edate = null;
        ArrayList<Integer> days_selected = new ArrayList<Integer>();
        for(int i=0;i<calendarRecord.getEvent_repeat().length();i++){
            if(calendarRecord.getEvent_repeat().charAt(i) == 'M'){
                days_selected.add(2);
            }
            if(calendarRecord.getEvent_repeat().charAt(i) == 'T'){
                days_selected.add(3);
            }
            if(calendarRecord.getEvent_repeat().charAt(i) == 'W'){
                days_selected.add(4);
            }
            if(calendarRecord.getEvent_repeat().charAt(i) == 'R'){
                days_selected.add(5);
            }
            if(calendarRecord.getEvent_repeat().charAt(i) == 'F'){
                days_selected.add(6);
            }
            if(calendarRecord.getEvent_repeat().charAt(i) == 'S'){
                days_selected.add(7);
            }
        }

        try {
            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
            sdate = new SimpleDateFormat("dd-MM-yyyy").parse(start_date.getText().toString());
            edate = new SimpleDateFormat("dd-MM-yyyy").parse(end_date.getText().toString());
            Calendar c = Calendar.getInstance();
            c.setTime(sdate);
            sdate = c.getTime();

            while(sdate.compareTo(edate)<=0){
                if(days_selected.contains(c.get(Calendar.DAY_OF_WEEK))) {
                    calendarRecord.setEvent_start_date(sm.format(sdate));
                    isInserted = dbHelper.insertData(calendarRecord);

                }
                c.add(Calendar.DATE, 1);
                sdate = c.getTime();
            }
        }catch (Exception e){
            Log.d("", "addClass: null pointer");
        }

        if(isInserted == true)
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), AcademicActivity.class);
        startActivity(i);
    }

}
