package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.academic.AcademicActivity;
import com.example.myapplication.journal.JournalActivity;
import com.example.myapplication.personal.PersonalActivity;

public class BottomNavigationFragment extends Fragment {
    public static BottomNavigationFragment getInstance(){
        return new BottomNavigationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle){
        View view = inflater.inflate(R.layout.bottom_navigation_fragment,parent,false);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent i = null;
                switch (item.getItemId()) {
                    case R.id.academic_calendar_menu:
                        i = new Intent(getActivity(), AcademicActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.personal_calendar_menu:
                        i = new Intent(getActivity(), PersonalActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.journal_menu:
                        i = new Intent(getActivity(), JournalActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.change_theme_menu:
                        i = new Intent(getActivity(), AcademicActivity.class);
                        startActivity(i);
                        return true;
                    default:
                        return false;
                }
            }
        });
        return view;
    }
}
