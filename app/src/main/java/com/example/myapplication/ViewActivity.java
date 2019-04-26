package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.journal.JournalActivity;

public class ViewActivity extends AppCompatActivity {
    Button button;
    public SharedPreferences preferences;
    public SharedPreferences.Editor edit_preferences;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme",0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String content = getIntent().getExtras().getString("Content");
        TextView text_view = (TextView)findViewById(R.id.textView);

        text_view.setText(content);
    }

    public void back(View view) {
        Intent i = new Intent(getApplicationContext(), JournalActivity.class);
        startActivity(i);
    }
}
