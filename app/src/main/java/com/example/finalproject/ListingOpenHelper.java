package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListingOpenHelper extends SQLiteOpenHelper {
    static final String TAG = "testing";

    static final String DATABASE_NAME = "listingDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_LISTINGS = "tableListings";
    static final String ID = "_id";
    static final String ADDRESS = "address";
    static final String RENT = "rent";
    static final String BEDROOMS = "bedrooms";
    static final String BATHROOMS = "bathrooms";
    static final String LEASELENGTH = "leaselength";
    static final String PHONE_NUMBER = "phoneNumber";
    static final String EMAIL = "email";

    public ListingOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate = "CREATE TABLE " + TABLE_LISTINGS + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ADDRESS + " TEXT, " +
                RENT + " TEXT, " +
                BEDROOMS  + " INTEGER, " +
                BATHROOMS + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                EMAIL + " TEXT, " +
                LEASELENGTH + " TEXT)";
        Log.d(TAG, "onCreate: " + sqlCreate);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected void insertListing(Listing listing){
        String sqlInsert = "INSERT INTO " + TABLE_LISTINGS + " VALUES(null, '" +
                listing.getAddress() + "', '" + listing.getRent() +
                "', " + listing.getBedrooms() +
                ", '" + listing.getBathrooms() +
                "', '" + listing.getLandlordPhone() +
                "', '" + listing.getLandlordEmail() +
                "', '" + listing.getLengthOfLease() + "')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    protected void updateListing(int id, Listing listing){
        String sqlUpdate = "UPDATE " + TABLE_LISTINGS + " SET " + ADDRESS + "='" + listing.getAddress() +
                "', " + RENT + "='" + listing.getRent() + "', " + BEDROOMS + "=" + listing.getBedrooms() +
                ", " + BATHROOMS + "='" + listing.getBathrooms() + "', " + PHONE_NUMBER + "='" +
                listing.getLandlordPhone() + "', " + EMAIL + "='" + listing.getLandlordEmail() +
                "', " + LEASELENGTH + "='" + listing.getLengthOfLease() + "' WHERE " + ID + "=" + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteAllListings(){
        String sqlDelete = "DELETE FROM " + TABLE_LISTINGS;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteListing(int id){
        String sqlDelete = "DELETE FROM " + TABLE_LISTINGS + " WHERE " + ID + "=" + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public Cursor getSelectAllListingsCursor(){
        String sqlSelect = "SELECT * FROM " + TABLE_LISTINGS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }

    public Listing getListing(long id){
        String sqlSelect = "SELECT * FROM " + TABLE_LISTINGS + " WHERE " + ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        cursor.moveToNext();

        String address = cursor.getString(1);
        String rent = cursor.getString(2);
        int bedrooms = cursor.getInt(3);
        String bathrooms = cursor.getString(4);
        String phoneNumber = cursor.getString(5);
        String email = cursor.getString(6);
        String leaseLength = cursor.getString(7);

        Listing listing = new Listing(address, rent, bedrooms, bathrooms, phoneNumber, email, leaseLength);

        db.close();
        cursor.close();
        return listing;
    }

    public Cursor getLandlordListings(String landlord){
        String sqlSelect = "SELECT * FROM " + TABLE_LISTINGS + " WHERE " + EMAIL + "= '" + landlord + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }


}
