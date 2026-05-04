package com.example.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE_NAME = "employee";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE employee (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT, designation TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
    }

    // INSERT
    public boolean insertData(String name, String location, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("location", location);
        cv.put("designation", designation);

        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    // VIEW
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM employee", null);
    }

    // UPDATE
    public boolean updateData(String id, String name, String location, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("location", location);
        cv.put("designation", designation);

        db.update(TABLE_NAME, cv, "id=?", new String[]{id});
        return true;
    }

    // DELETE
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{id});
    }
}
