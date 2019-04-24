package com.example.myapplication.theme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources.*;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.academic.AcademicActivity;
import com.example.myapplication.journal.JournalActivity;
import com.example.myapplication.personal.PersonalActivity;

import java.sql.Types;

public class ChangeThemeActivity extends AppCompatActivity {
    public SharedPreferences.Editor edit_preferences;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme",0));

        Log.d("info", "**************************8888******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************8888*****************onCreate: " + R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);
        Button button_blue = (Button) findViewById(R.id.button_color_blue);
        //button_blue.setBackgroundResource(R.color.blue);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.academic_calendar_menu).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent i = null;
                switch (item.getItemId()) {
                    case R.id.academic_calendar_menu:
                        i = new Intent(getApplicationContext(), AcademicActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.personal_calendar_menu:
                        i = new Intent(getApplicationContext(), PersonalActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.journal_menu:
                        i = new Intent(getApplicationContext(), JournalActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.change_theme_menu:
                        i = new Intent(getApplicationContext(), ChangeThemeActivity.class);
                        startActivity(i);
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.setTitle("");
    }

    void OnClickButtonBlue(View v) {
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme_ColorBlue);
        edit_preferences.commit();
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
        //call on create where new theme is applied
    }

    void OnClickButtonNavyBlue(View v) {
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme_ColorNavyBlue);
        edit_preferences.commit();
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
        //call on create where new theme is applied
    }
    void OnClickButtonSkyBlue(View v) {
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme_ColorBlue);
        edit_preferences.commit();
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
        //call on create where new theme is applied
    }

    void OnClickButtonMaroon(View v) {
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme_ColorMaroon);
        edit_preferences.commit();
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
        //call on create where new theme is applied
    }


    void OnClickButtonGreen(View v) {
        edit_preferences = preferences.edit();
        edit_preferences.putInt("theme" , R.style.AppTheme_ColorGreen);
        edit_preferences.commit();
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
        //call on create where new theme is applied
    }
}
