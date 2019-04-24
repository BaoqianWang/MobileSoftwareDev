/*
package com.example.myapplication;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.myapplication.R;

public class Res extends Resources {

    Context c;

    SharedPreferences preferences;

    public Res(Resources original) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
        c = original.get;
    }

    @Override public int getColor(int id) throws NotFoundException {
        return getColor(id, null);
    }

    @Override public int getColor(int id, Theme theme) throws NotFoundException {
        switch (getResourceEntryName(id)) {
            case "colorSelected1":

                preferences = PreferenceManager.getDefaultSharedPreferences(Res.this);
                Log.d("info", "**************************####******************onCreate: " + preferences.getInt("theme",0));
                Log.d("info", "**************************####*****************onCreate: " + R.style.AppTheme);

                if(preferences.getInt("theme") == R.style.AppTheme_ColorBlue){
                    return getColor(R.color.colorAccent);
                }
                  // used as an example. Change as needed.
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return super.getColor(id, theme);
                }
                return super.getColor(id);
        }
    }
}*/
