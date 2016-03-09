package com.ass1.mandeep.quicknote;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;



/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class CreateNoteActivity extends ActionBarActivity {

    EditText TxtName;
    EditText TxtContent;
    Button BtnSave;
    Button BtnDiscard;
    MyOpenHelper myOpenHelper;
    Long Rowid; // Value of row retrieved from intent when editing notes
    private final static String DATABASE_NAME = "SQLiteDatabasePractical.db";
    private final static int VERSION_NO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myOpenHelper = new MyOpenHelper(this, DATABASE_NAME, null, VERSION_NO);
        setContentView(R.layout.activity_create_note);
        TxtName = (EditText)findViewById(R.id.txtNoteName);
        TxtContent = (EditText)findViewById(R.id.txtContent);
        BtnSave = (Button)findViewById(R.id.btnSave);
        BtnDiscard = (Button)findViewById(R.id.btnDiscard);
        Intent editNoteIntent = getIntent();
        Rowid = editNoteIntent.getLongExtra(myOpenHelper.COLUMN_ID, -1);
        // if there is some value passed in Rowid i.e. not creating the note for first time
        if(Rowid != -1)
        {
            SQLiteDatabase mDb = myOpenHelper.getReadableDatabase();
            Cursor mCursor =

                    mDb.rawQuery("SELECT * FROM notes WHERE _id = ?", new String[]{(String.valueOf(Rowid))});
            while(mCursor.moveToNext()) {
                /*String name = mCursor.getString(mCursor.getColumnIndex("name"));
                String content = mCursor.getString(mCursor.getColumnIndex("content"));*/
                TxtName.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(myOpenHelper.COLUMN_NAME)));
                TxtContent.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(myOpenHelper.COLUMN_CONTENT)));
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
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

    // onClick for Save button in CreateNoteActivity
    public void SaveNote(View view) {
        //if there is intent passed from show notes activity to edit, then it  will update the note
        if(Rowid != -1)
        {
            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            String dayOfMonthStr = String.valueOf(dayOfMonth);
            int Month = cal.get(Calendar.MONTH) + 1;
            String MonthStr = String.valueOf(Month);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            String hourStr = String.valueOf(hour);
            int min = cal.get(Calendar.MINUTE);
            String minStr = String.valueOf(min);
            String timeStr = dayOfMonthStr + "/" + MonthStr + " " + hourStr + ":" + minStr;
            myOpenHelper.updateNote(Rowid,TxtName.getText().toString() , TxtContent.getText().toString(), timeStr);
            finish();
        }
        //if there's no intent passed it will save the new note.
        else {
            String name = TxtName.getText().toString();
            String content = TxtContent.getText().toString();
            String content_short = content.substring(0, 5) + "...";
            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            String dayOfMonthStr = String.valueOf(dayOfMonth);
            int Month = cal.get(Calendar.MONTH) + 1;
            String MonthStr = String.valueOf(Month);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            String hourStr = String.valueOf(hour);
            int min = cal.get(Calendar.MINUTE);
            String minStr = String.valueOf(min);
            String timeStr = dayOfMonthStr + "/" + MonthStr + " " + hourStr + ":" + minStr;
            myOpenHelper = new MyOpenHelper(this, DATABASE_NAME, null, VERSION_NO);
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            //Creating ContentValues object to  insert values in table
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("content", content);
            values.put("cont_short", content_short);
            values.put("date", timeStr);
            db.insert(myOpenHelper.TABLE_NAME, null, values);
            finish();
        }
    }

    public void DiscardNote(View view) {
        generateDiscardDialog();
    }

    private void generateDiscardDialog() {
        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);

        // Specify the list in the dialog using the array
        builder.setTitle("Discard Note").setMessage("Are you sure you want to Discard this note?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
        //create and show list dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
