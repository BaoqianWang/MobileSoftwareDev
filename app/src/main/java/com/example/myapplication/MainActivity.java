package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.academic.AcademicActivity;
import com.example.myapplication.journal.JournalActivity;
import com.example.myapplication.personal.PersonalActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button button;
    public SharedPreferences preferences;
    public SharedPreferences.Editor edit_preferences;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme);
        edit_preferences.commit();
        Log.d("info", "********************************************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "********************************************onCreate: " + R.style.AppTheme);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

       // Log.d("new&&&&&&&&&&&&&&&&", "onCreate: "+Arrays.toString( dbHelper.getAllData().toArray()));
        Intent i = new Intent(getApplicationContext(), AcademicActivity.class);
        startActivity(i);
    }


/*
    public void viewAll() {
        Cursor res = dbHelper.getAllData();
        if(res.getCount() == 0) {
            // show message
            Log.d("***************asha", "viewAll: Data not found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("calendar_type :"+ res.getString(1)+"\n");
            buffer.append("event_type :"+ res.getString(2)+"\n");
            buffer.append("event_name :"+ res.getString(3)+"\n\n");
            buffer.append("event_start_date :"+ res.getString(4)+"\n\n");
            buffer.append("event_end_date :"+ res.getString(5)+"\n\n");
            buffer.append("event_repeat :"+ res.getString(6)+"\n\n");

        }

        // Show all data
        Log.d("***************asha", buffer.toString());

    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void launchAcademicActivity(View view) {
        Intent i = new Intent(getApplicationContext(), AcademicActivity.class);
        startActivity(i);
    }

    public void launchJournalActivity(View view) {
        Intent i = new Intent(getApplicationContext(), JournalActivity.class);
        startActivity(i);
    }

    public void launchPersonalActivity(View view) {
        Intent i = new Intent(getApplicationContext(), PersonalActivity.class);
        startActivity(i);
    }
}
