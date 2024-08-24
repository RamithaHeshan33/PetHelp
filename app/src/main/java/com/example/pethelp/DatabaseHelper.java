package com.example.pethelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pethelp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_PETS = "pets";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PET_NAME = "pet_name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_BREED = "breed";
    private static final String COLUMN_IMAGE_URI = "image_uri";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "onCreate called, creating tables");

        // Users Table
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT)");

        // Pets Table
        db.execSQL("CREATE TABLE " + TABLE_PETS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PET_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_BREED + " TEXT, " +
                COLUMN_IMAGE_URI + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);
        onCreate(db);
    }

    // User Methods

    public Boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    // Pet Methods
    public boolean addPet(String petName, int age, String color, String sex, String breed, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PET_NAME, petName);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_COLOR, color);
        values.put(COLUMN_SEX, sex);
        values.put(COLUMN_BREED, breed);
        values.put(COLUMN_IMAGE_URI, imageUri);
        long result = db.insert(TABLE_PETS, null, values);
        return result != -1;
    }

    public boolean updateUser(SQLiteDatabase db, String email, String newEmail, String newPassword) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, newEmail);
        values.put(COLUMN_PASSWORD, newPassword);
        int result = db.update(TABLE_USERS, values, COLUMN_EMAIL + " = ?", new String[]{email});
        return result > 0;
    }

}
