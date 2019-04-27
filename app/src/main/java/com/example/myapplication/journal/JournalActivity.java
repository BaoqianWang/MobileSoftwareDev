package com.example.myapplication.journal;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.CalendarRecord;
import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.ViewActivity;
import com.example.myapplication.academic.AcademicActivity;
import com.example.myapplication.journal.JournalActivity;
import com.example.myapplication.personal.PersonalActivity;
import com.example.myapplication.theme.ChangeThemeActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String selected_date;

    public String getSelected_date() {
        return selected_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme",0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.journal_menu).setChecked(true);
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
        final CalendarView cv = (CalendarView)findViewById(R.id.calendarView);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        selected_date = sdf.format(date);
        displayItems();


        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                    /*long new_date = cv.getDate();
                    Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                */
                selected_date = dayOfMonth + "-" + new DecimalFormat("00").format(month+1) + "-" + year;
                Log.d("asha", "****************^^^^^^^^^^^^^^^^^^^^^^^^^^^^onCreate: position"+selected_date);
                displayItems();
            }
        });
    }

    public void addNote(View view) {

        Intent i = new Intent(JournalActivity.this, AddNoteActivity.class);
        i.putExtra("selected_date", selected_date);
        startActivity(i);
    }
    ArrayAdapter<CalendarRecord> adapter;
    ArrayList<CalendarRecord> listItems=new ArrayList<CalendarRecord>();
    ListView list;
    protected void onResume() {
        Log.d("**************", "onResume: "+selected_date);
        displayItems();
        super.onResume();
    }
    public void displayItems() {
        DBHelper dbHelper = new DBHelper(this);

        listItems = dbHelper.getAllDataForDate(selected_date);

        //listItems = dbHelper.getAllData();
        //Log.d("hey", "%%%%%%%%%%%%%%%%%%%%%%%%%%%onResume: " + listItems.size());
        if (listItems != null && listItems.size()!=0) {
            ArrayList<CalendarRecord> final_array = new ArrayList<>();
            for(int i=0;i<listItems.size();i++){
                Log.d("CR", "**********************displayItems: "+listItems.get(i).getCalendar_type());
                if(!(listItems.get(i).getCalendar_type().trim().equalsIgnoreCase("Journal"))){
                    Log.d("CRN", "**********************displayItems: "+listItems.get(i).toString());

                } else {
                    final_array.add(listItems.get(i));
                }
            }
            listItems.clear();
            listItems.addAll(final_array);
            Log.d("hey", "@#%$#@$#%^&*(%%%%%%%%%%%%%%%%%%%%%%%%%%%onResume: " + listItems.size());
            if(listItems.size()==0){
                if(adapter!=null) {
                    adapter.clear();
                    list.setAdapter(adapter);
                }
                return;
            }
            Log.d("&&&&&&&&&&&&&&&&&", "onResume: " + listItems.size());
            list = (ListView) findViewById(R.id.user_list);

            adapter = new ArrayAdapter<CalendarRecord>(JournalActivity.this, android.R.layout.simple_spinner_item, listItems) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView item = (TextView) super.getView(position, convertView, parent);
                    item.setPadding(10,10,10,10);
                    int theme =  preferences.getInt("theme",0);
                    if(theme==R.style.AppTheme){
                        item.setTextColor(Color.parseColor("#FF9500"));
                    }
                    if(theme==R.style.AppTheme_ColorBlue){
                        item.setTextColor(Color.parseColor("#0288D1"));
                    }
                    if(theme==R.style.AppTheme_ColorGreen){
                        item.setTextColor(Color.parseColor("#00796B"));
                    }
                    if(theme==R.style.AppTheme_ColorNavyBlue){
                        item.setTextColor(Color.parseColor("#3F51B5"));
                    }
                    if(theme==R.style.AppTheme_ColorSkyBlue){
                        item.setTextColor(Color.parseColor("#03A9F4"));
                    }
                    if(theme==R.style.AppTheme_ColorMaroon){
                        item.setTextColor(Color.parseColor("#C2185B"));
                    }

                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                    return item;
                }
            };


            list.setAdapter(adapter);
            list.setOnItemClickListener( listPairedClickItem );
        } else {
            if(adapter!=null) {
                adapter.clear();
                list.setAdapter(adapter);
            }
        }
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    private AdapterView.OnItemClickListener listPairedClickItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView < ? > arg0, View arg1, int arg2, long arg3) {

            /*String info = ((TextView) arg1).getText().toString();
            Toast.makeText(getBaseContext(), "Item " + info, Toast.LENGTH_LONG).show();
            int theme = preferences.getInt("theme", 0);


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(JournalActivity.this,R.style.MyDialogTheme);
            alertDialogBuilder.setMessage(listItems.get(arg2).getCalendar_type() + " : " + listItems.get(arg2).getEvent_name());
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();*/

            Intent i = new Intent(JournalActivity.this, ViewActivity.class);
            i.putExtra("Content", "Name : " + listItems.get(arg2).getEvent_name()
                    + "\n \n" + listItems.get(arg2).getEvent_end_date());
            startActivity(i);
        }
    };
}
