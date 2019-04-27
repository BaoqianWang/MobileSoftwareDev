package com.example.myapplication.personal;

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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CalendarRecord;
import com.example.myapplication.DBHelper;
import com.example.myapplication.academic.AcademicActivity;
import com.example.myapplication.academic.AddAssignmentActivity;
import com.example.myapplication.academic.AddClassAcademicActivity;
import com.example.myapplication.academic.AddExamActivity;
import com.example.myapplication.academic.AddNewReminderAcademicActivity;
import com.example.myapplication.academic.AddReminderAcademicActivity;
import com.example.myapplication.journal.JournalActivity;
import com.example.myapplication.personal.AddGoalPersonalActivity;
import com.example.myapplication.personal.AddTaskPersonalActivity;
import com.example.myapplication.R;
import com.example.myapplication.theme.ChangeThemeActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class PersonalActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    SharedPreferences preferences;
    String selected_date;
    ArrayAdapter<CalendarRecord> adapter;
    ArrayList<CalendarRecord> listItems=new ArrayList<CalendarRecord>();
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme", 0));
        Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);
        setTheme(preferences.getInt("theme", 0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        //* starts before 1 month from now *//*
        Calendar startDate = Calendar.getInstance();

        startDate.add(Calendar.MONTH, -1);

        //* ends after 1 month from now *//*
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /*HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.academicCalendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });*/
        final CalendarView cv = (CalendarView) findViewById(R.id.personalCalendarView);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        selected_date = sdf.format(date);
        displayItems();


        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                    /*long new_date = cv.getDate();
                    Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                */
                month = month + 1;

                selected_date = new DecimalFormat("00").format(dayOfMonth) + "-" + new DecimalFormat("00").format(month) + "-" + year;
                Log.d("asha", "****************^^^^^^^^^^^^^^^^^^^^^^^^^^^^onCreate: position" + selected_date);
                displayItems();
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.personal_calendar_menu).setChecked(true);
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

    public void launchAddTask(View view) {
       /* Intent i = new Intent(getApplicationContext(), AddNewReminderAcademicActivity.class);
        startActivity(i);*/
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(PersonalActivity.this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.personal_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.add_goals:
                i = null;
                i = new Intent(getApplicationContext(), AddGoalPersonalActivity.class);
                startActivity(i);
                return true;
            case R.id.add_tasks:
                i = null;
                i = new Intent(getApplicationContext(), AddTaskPersonalActivity.class);
                startActivity(i);
                return true;
            case R.id.add_reminder:
                i = null;
                i = new Intent(getApplicationContext(), AddNewReminderPersonalActivity.class);
                startActivity(i);
                return true;
            default:
                return false;
        }
    }

    private AdapterView.OnItemClickListener listPairedClickItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView < ? > arg0, View arg1, int arg2, long arg3) {

            String info = ((TextView) arg1).getText().toString();
            //Toast.makeText(getBaseContext(), "Item " + info, Toast.LENGTH_LONG).show();
            int theme = preferences.getInt("theme", 0);


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PersonalActivity.this,R.style.MyDialogTheme);
            if(listItems.get(arg2).getEvent_type().equalsIgnoreCase("goal")) {
                alertDialogBuilder.setMessage(listItems.get(arg2).getEvent_type() + " : " + listItems.get(arg2).getEvent_name() +
                        "\n" + "Start Date : " + listItems.get(arg2).getEvent_start_date() +
                        "\n" + "End Date : " + listItems.get(arg2).getEvent_end_date() +
                        "\n" + "Repeats : " + listItems.get(arg2).getEvent_repeat()
                );
            } else {
                alertDialogBuilder.setMessage(listItems.get(arg2).getEvent_type() + " : " + listItems.get(arg2).getEvent_name() +
                        "\n" + "Start Date : " + listItems.get(arg2).getEvent_start_date() +
                        "\n" + "End Date : " + listItems.get(arg2).getEvent_end_date()
                );
            }
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
            TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
            textView.setTextSize(25);

        }
    };

    /*public void launchAddTask(View view) {
        Intent i = new Intent(getApplicationContext(), AddTaskPersonalActivity.class);
        startActivity(i);
    }*/
    @Override
    protected void onResume() {
        Log.d("**************", "onResume: "+selected_date);
        displayItems();
        super.onResume();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    public void displayItems() {
        DBHelper dbHelper = new DBHelper(this);
        listItems = dbHelper.getAllDataForDate(selected_date);

        //listItems = dbHelper.getAllData();
        if (listItems != null && listItems.size() != 0) {
            ArrayList<CalendarRecord> final_array = new ArrayList<>();
            for (int i = 0; i < listItems.size(); i++) {
                Log.d("CR", "**********************displayItems: " + listItems.get(i).getCalendar_type());
                if (!(listItems.get(i).getCalendar_type().trim().equalsIgnoreCase("Personal"))) {
                    Log.d("CRN", "**********************displayItems: " + listItems.get(i).toString());

                } else {
                    final_array.add(listItems.get(i));
                }
            }
            listItems.clear();
            listItems.addAll(final_array);
            Log.d("hey", "@#%$#@$#%^&*(%%%%%%%%%%%%%%%%%%%%%%%%%%%onResume: " + listItems.size());
            if (listItems.size() == 0) {
                if (adapter != null) {
                    adapter.clear();
                    list.setAdapter(adapter);
                }
                return;
            }
            Log.d("&&&&&&&&&&&&&&&&&", "onResume: " + listItems.size());
            list = (ListView) findViewById(R.id.user_list);

            adapter = new ArrayAdapter<CalendarRecord>(getApplicationContext(), android.R.layout.simple_spinner_item, listItems) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView item = (TextView) super.getView(position, convertView, parent);
                    item.setPadding(10, 10, 10, 10);
                    int theme = preferences.getInt("theme", 0);
                    if (theme == R.style.AppTheme) {
                        item.setTextColor(Color.parseColor("#FF9500"));
                    }
                    if (theme == R.style.AppTheme_ColorBlue) {
                        item.setTextColor(Color.parseColor("#0288D1"));
                    }
                    if (theme == R.style.AppTheme_ColorGreen) {
                        item.setTextColor(Color.parseColor("#00796B"));
                    }
                    if (theme == R.style.AppTheme_ColorNavyBlue) {
                        item.setTextColor(Color.parseColor("#3F51B5"));
                    }
                    if (theme == R.style.AppTheme_ColorSkyBlue) {
                        item.setTextColor(Color.parseColor("#03A9F4"));
                    }
                    if (theme == R.style.AppTheme_ColorMaroon) {
                        item.setTextColor(Color.parseColor("#C2185B"));
                    }

                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                    return item;
                }
            };
            list.setAdapter(adapter);
            list.setOnItemClickListener(listPairedClickItem);
        } else {
            if (adapter != null) {
                adapter.clear();
                list.setAdapter(adapter);
            }
        }
    }
}