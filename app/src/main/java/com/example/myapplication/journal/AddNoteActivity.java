package com.example.myapplication.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CalendarRecord;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.academic.AcademicActivity;

public class AddNoteActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String name;
    View dialoglayout;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    protected void onCreate(Bundle savedInstanceState) {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme", 0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme", 0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);
        LayoutInflater inflater = getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.my_custom_layout, null);
        builder = new AlertDialog.Builder(this);
        builder.setView(dialoglayout);
        dialog = builder.create();
        dialog.show();
    }

    public void saveNote(View view) {

        EditText subject = (EditText) findViewById(R.id.note);

        CalendarRecord calendarRecord = new CalendarRecord();
        DBHelper dbHelper = new DBHelper(this);
        calendarRecord.setCalendar_type("Journal");
        calendarRecord.setEvent_type("Note");
        //calendarRecord.setEvent_name(subject.getText().toString());
        calendarRecord.setEvent_name(name);
        calendarRecord.setEvent_end_date(subject.getText().toString());
        JournalActivity journalActivity = new JournalActivity();
        Bundle extras = getIntent().getExtras();
        String selected_date = extras.getString("selected_date");
        calendarRecord.setEvent_start_date(selected_date);
        Log.d("checkdate", "*********************************saveNote: "+selected_date);
        boolean isInserted = dbHelper.insertData(calendarRecord);
        if(isInserted == true)
            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),JournalActivity.class);
        startActivity(i);
    }

    public void addName(View view) {
        EditText edit_text1 = (EditText)dialoglayout.findViewById(R.id.name);
        name = edit_text1.getText().toString();
        dialog.dismiss();
        Log.d("name", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&addName: "+name);
    }
}
