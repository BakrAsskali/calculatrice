package com.example.calculatice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_HISTORY = "historique";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXPRESSION = "expression";
    public static final String COLUMN_RESULT = "result";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPRESSION + " TEXT, " +
                COLUMN_RESULT + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_HISTORY;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addData(String expression, double result) {
        SQLiteDatabase db = this.getWritableDatabase();
        String addDataQuery = "INSERT INTO " + TABLE_HISTORY + " (" +
                COLUMN_EXPRESSION + ", " +
                COLUMN_RESULT + ") VALUES ('" +
                expression + "', " +
                result + ")";
        db.execSQL(addDataQuery);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String getAllDataQuery = "SELECT * FROM " + TABLE_HISTORY;
        Cursor data = db.rawQuery(getAllDataQuery, null);
        return data;
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAllDataQuery = "DELETE FROM " + TABLE_HISTORY;
        db.execSQL(deleteAllDataQuery);
    }
}