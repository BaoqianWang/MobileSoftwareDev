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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CalendarRecord;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.theme.ChangeThemeActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAssignmentActivity extends AppCompatActivity {
    SharedPreferences preferences;
    private int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme",0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

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
                DatePickerDialog dpd = new DatePickerDialog(AddAssignmentActivity.this,
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


        this.setTitle("");
    }
    public void launchAddNewReminder(View v){
        Intent i = new Intent(getApplicationContext(), AddNewReminderAcademicActivity.class);
        startActivity(i);
    }

    public void addAssignment(View view) {
        EditText subject = (EditText) findViewById(R.id.textView2);
        TextView start_date = (TextView) findViewById(R.id.date);

        CalendarRecord calendarRecord = new CalendarRecord();
        DBHelper dbHelper = new DBHelper(this);
        calendarRecord.setCalendar_type("Academic");
        calendarRecord.setEvent_type("Assignment");
        calendarRecord.setEvent_name(subject.getText().toString());
        calendarRecord.setEvent_start_date(start_date.getText().toString());

        boolean isInserted = dbHelper.insertData(calendarRecord);
        if(isInserted == true)
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), AcademicActivity.class);
        startActivity(i);
    }
}
