package com.ass1.mandeep.quicknote; /**
 * Created by Mandeep on 01-Oct-15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "SQLiteDatabasePractical.db";
    private final static int VERSION_NO = 1;
    SQLiteDatabase mDb = getReadableDatabase();
    public static final String TABLE_NAME = "Notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_CONTENT_SHORT = "cont_short";
    public static final String COLUMN_DATE  = "date";
    private static final String DATABASE_CREATE = "create table "+ TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " + COLUMN_CONTENT +
            " text not null, "+ COLUMN_CONTENT_SHORT +
            " text not null, " + COLUMN_DATE + " text not null);";
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean updateNote(long rowId, String title, String body,String date) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, title);
        args.put(COLUMN_CONTENT, body);
        args.put(COLUMN_CONTENT_SHORT, body.substring(0,5)+"...");
        args.put(COLUMN_DATE, date);

        //One more parameter is added for data
        return mDb.update(TABLE_NAME, args, COLUMN_ID + "=" + rowId, null) > 0;
    }
}

