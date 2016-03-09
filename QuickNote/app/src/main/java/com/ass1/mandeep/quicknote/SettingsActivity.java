package com.ass1.mandeep.quicknote;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class SettingsActivity extends ActionBarActivity {
    Integer p = 0;
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String PREFS_KEY = "AOP_PREFS_String";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
        final TextView txtSize = (TextView)findViewById(R.id.lblSize);

        final SeekBar seek = (SeekBar)findViewById(R.id.seekBar);
        //check for stored size and set seekbar to that size
        Context context = getApplicationContext();
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        Integer size = settings.getInt(PREFS_KEY, -1);
        final Switch editSwitch = (Switch)findViewById(R.id.lock);
        Boolean checked = settings.getBoolean("IsChecked", true);
        //updates the switch according to value stored in shared prefs
        editSwitch.setChecked(checked);
        if(size != -1)
        {
            //Updates the seekbar progress based on value stored in shared prefs
            seek.setProgress(size);
            txtSize.setText(size.toString());
        }
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress;
                txtSize.setText(p.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // Value of seek progress gets saved in shared preferences onStopTrackingTouch
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Context context = getApplicationContext();
                SharedPreferences settings;
                SharedPreferences.Editor editor;
                settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //Step 1
                editor = settings.edit(); // Step 2

                editor.putInt(PREFS_KEY, p); // Step 3
                editor.commit();
            }
        });


        //value of isChecked is updated and saved in shared prefs on this event
        editSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context context = getApplicationContext();
                SharedPreferences settings;
                SharedPreferences.Editor editor;
                settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //Step 1
                editor = settings.edit(); // Step 2

                editor.putBoolean("IsChecked", isChecked); // Step 3
                editor.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void SaveSettings(View view) {
    }
}
