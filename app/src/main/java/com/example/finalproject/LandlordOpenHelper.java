package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LandlordOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "landlordDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_LANDLORDS = "tableLandlords";


    static final String ID = "_id";
    static final String EMAIL = "email";
    static final String PASSWORD = "password";

    public LandlordOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate = "CREATE TABLE " + TABLE_LANDLORDS  + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL + " TEXT, " + PASSWORD + " TEXT)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected void insertLandlord(Landlord landlord){
        String sqlInsert = "INSERT INTO " + TABLE_LANDLORDS + " VALUES(null, '" +
                landlord.getEmail() + "', '" + landlord.getPassword() + "')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    protected void updateLandlord(int id, Landlord landlord){
        String sqlUpdate = "UPDATE " + TABLE_LANDLORDS + " SET " + EMAIL + "='" + landlord.getEmail() +
                "', " + PASSWORD + "='" + landlord.getPassword() + "' WHERE " + ID + "=" + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteAllLandlords(){ //used for testing purposes
        String sqlDelete = "DELETE FROM " + TABLE_LANDLORDS;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public Cursor getSelectAllListingsCursor(){
        String sqlSelect = "SELECT * FROM " + TABLE_LANDLORDS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }


}
