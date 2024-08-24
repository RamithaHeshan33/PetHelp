package com.example.pethelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddFoodDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "petfood.db";
    private static final int DB_VERSION = 1;

    public AddFoodDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Food Table
        String createFoodsTable = "CREATE TABLE foods(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "foodid TEXT," +
                "s1 TEXT," +
                "s2 TEXT," +
                "s3 INTEGER)";
        db.execSQL(createFoodsTable);


        String createArticlesTable = "CREATE TABLE articles(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "description TEXT," +
                "link TEXT)";
        db.execSQL(createArticlesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFood(SQLiteDatabase db, String foodid, String s1, String s2, String s3) {
        ContentValues values = new ContentValues();
        values.put("foodid", foodid);
        values.put("s1", s1);
        values.put("s2", s2);
        values.put("s3", s3);
        db.insert("foods", null, values);
    }

    public void deleteMark(SQLiteDatabase db, String foodid) {
        db.delete("foods", "foodid=?", new String[]{foodid});
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM foods", null);
    }

    public boolean addArticle(SQLiteDatabase db, String title, String description, String link) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("link", link);

        long result = db.insert("articles", null, values);
        return result != -1;
    }

}
