package com.example.dell.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActionsNote {
    private SQLiteDatabase database;
    private DataBaseNotes dbHelper;

    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    public ActionsNote() {

    }


    public ActionsNote open(Context context) throws SQLException {
        dbHelper = new DataBaseNotes(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    public long createNote(String Titre,String Det) {
        ContentValues values = new ContentValues();
        values.put(DataBaseNotes.COLUMN_COMMENT, Titre);
        values.put(DataBaseNotes.COLUMN_DETAfILE,Det);
        values.put(DataBaseNotes.COLUMN_DATE,currentDate);
     long rowInserted =  database.insert(DataBaseNotes.TABLE_COMMENTS, null,
                values);

return  rowInserted;
    }


    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> Notes_in = new ArrayList<>();
        String[] Notes = new String[] {
                DataBaseNotes.COLUMN_ID,
                DataBaseNotes.COLUMN_COMMENT,
                DataBaseNotes.COLUMN_DETAfILE,
                DataBaseNotes.COLUMN_DATE,
        };
        Cursor cursor = database.rawQuery("SELECT * FROM "+DataBaseNotes.TABLE_COMMENTS,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Note nt = new Note(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            Notes_in.add(nt);
          //  Log.i(MainActivity.ACTIVITY_SERVICE, cursor.getString(1));
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return Notes_in;
    }
    public Note getNote(int pos)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM "+DataBaseNotes.TABLE_COMMENTS+" WHERE "+DataBaseNotes.COLUMN_ID+" = "+pos,null);
        cursor.moveToFirst();
   Note note = new Note(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
   return  note;
    }


    public boolean updateNote(String pos,String Titre,String Det)
    {
        ContentValues values = new ContentValues();
        values.put(DataBaseNotes.COLUMN_COMMENT, Titre);
        values.put(DataBaseNotes.COLUMN_DETAfILE,Det);
        values.put(DataBaseNotes.COLUMN_DATE,currentDate);
try {
    long update = database.update(DataBaseNotes.TABLE_COMMENTS, values, DataBaseNotes.COLUMN_ID + "=" + pos, null);
return  true;
}
catch (Exception r){return false;}
     //   myDataBase.execSQL(strSQL);

    }

    public boolean DeletNote(String pos)
    {
        try {
            database.delete(DataBaseNotes.TABLE_COMMENTS, DataBaseNotes.COLUMN_ID + "=" + pos, null);
        return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
