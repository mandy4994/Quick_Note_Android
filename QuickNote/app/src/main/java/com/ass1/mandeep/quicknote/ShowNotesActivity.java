package com.ass1.mandeep.quicknote;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class ShowNotesActivity extends ListActivity {
    int size = 0;
    MyOpenHelper myOpenHelper;
    private final static String DATABASE_NAME = "SQLiteDatabasePractical.db";
    private final static int VERSION_NO = 1;
    public TextView TxtName;
    public TextView TxtContent;
    public TextView TxtDate;

    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String PREFS_KEY = "AOP_PREFS_String";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        myOpenHelper = new MyOpenHelper(this, DATABASE_NAME, null, VERSION_NO);
   /*     final LayoutInflater factory = getLayoutInflater();

        final View view = factory.inflate(R.layout.rowlayout, null);
        TxtName = (TextView)view.findViewById(R.id.lblName);
        TxtContent = (TextView)view.findViewById(R.id.lblContent);
        TxtDate = (TextView)view.findViewById(R.id.lblDate);
        Context context = getApplicationContext();
        SharedPreferences settings;
        int size;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        size = settings.getInt(PREFS_KEY, 25);
        TxtName.setTextSize(size);
        TxtName.setTextColor(-16711681);
        TxtContent.setTextSize((0.6f * size));
        TxtDate.setTextSize((0.6f * size));*/
        displayDataInTable();
        //2
        /*final SeekBar seek = (SeekBar)view.findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress;
                TxtName.setTextSize(p);
                TxtContent.setTextSize((0.6f * p));
                TxtDate.setTextSize((0.6f * p));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void displayDataInTable() {
        List<Note> values = queryTable();

        if(values != null)
        {
            NotesArrayAdapter adapter = new NotesArrayAdapter(this, values);
            setListAdapter(adapter);
        }
    }

    private List<Note> queryTable() {
        List<Note> NoteList = new ArrayList<Note>();
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(myOpenHelper.TABLE_NAME, null, null, null, null, null, null);

        /*String[] from = new String[] { myOpenHelper.COLUMN_NAME ,myOpenHelper.COLUMN_DATE, myOpenHelper.COLUMN_CONTENT_SHORT};
        int[] to = new int[] { R.id.lblName ,R.id.lblDate, R.id.lblContent};

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.rowlayout, cursor, from, to);
        setListAdapter(notes);
        TxtName.setTextSize(size);
        TxtName.setTextColor(-16711681);
        TxtContent.setTextSize((0.6f * size));
        TxtDate.setTextSize((0.6f * size));*/

        //Looping till the end of table
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String short_content = cursor.getString(cursor.getColumnIndex("cont_short"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            NoteList.add(new Note(name, content, short_content, date));
        }
        return NoteList;

    }

    //Passes intent to CreateNoteActivity to update note on clicking list item on if switch is on in settings
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Boolean checked;
        Context context = getApplicationContext();
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        checked = settings.getBoolean("IsChecked", true);
        if(checked == true) {
            super.onListItemClick(l, v, position, id);
            Intent i = new Intent(this, CreateNoteActivity.class);
            i.putExtra(myOpenHelper.COLUMN_ID, (id + 1));
            startActivity(i);
            finish();
        }

    }

}


