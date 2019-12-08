package com.example.dell.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseNotes extends SQLiteOpenHelper  {

    public static final String TABLE_COMMENTS = "Note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "Titre";
    public static final String COLUMN_DETAfILE = "Detaile";
    public static final String COLUMN_DATE = "Date";
    private static final String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT+" TEXT,"+
            COLUMN_DETAfILE+" TEXT,"+COLUMN_DATE +" TEXT)";

    public DataBaseNotes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseNotes.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
