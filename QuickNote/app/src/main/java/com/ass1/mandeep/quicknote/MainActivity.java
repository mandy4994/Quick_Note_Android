package com.ass1.mandeep.quicknote;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class MainActivity extends Activity {

    Button BtnCreate, BtnShowNotes, BtnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnCreate = (Button)findViewById(R.id.btn_create);
        BtnShowNotes = (Button)findViewById(R.id.btn_show);
        BtnAbout =(Button)findViewById(R.id.btn_about);
        BtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNoteintent = new Intent(getApplicationContext(), CreateNoteActivity.class);
                createNoteintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(createNoteintent);
            }
        });

        BtnShowNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNoteintent = new Intent(getApplicationContext(), ShowNotesActivity.class);
                showNoteintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(showNoteintent);
            }
        });

        BtnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Aboutintent = new Intent(getApplicationContext(), AboutActivity.class);
                Aboutintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Aboutintent);
            }
        });
    }

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

    public void GoToSettings(View view) {
        Intent settingsintent = new Intent(this, SettingsActivity.class);
        settingsintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(settingsintent);
    }

    public void ExitApp(View view) {
        finish();
    }

    /*public void GoToCreate(View view) {
        Intent createNoteintent = new Intent(this, CreateNoteActivity.class);
        createNoteintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(createNoteintent);
    }

    public void ShowNotes(View view) {
        Intent showNoteintent = new Intent(this, ShowNotesActivity.class);
        showNoteintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(showNoteintent);
    }

    public void OpenAbout(View view) {
        Intent Aboutintent = new Intent(this, AboutActivity.class);
        Aboutintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Aboutintent);
    }*/
}
