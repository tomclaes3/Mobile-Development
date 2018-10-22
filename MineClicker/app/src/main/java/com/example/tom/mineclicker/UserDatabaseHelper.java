package com.example.tom.mineclicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tom.mineclicker.UserContract.userStats;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mine.db";

    public static final
    String DATABASE_CREATE =
            "CREATE TABLE "
                    + userStats.TABLE_NAME + "(" +
                    userStats._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    userStats.COL_FLOOR + " INTEGER, " +
                    userStats.COL_CLICKS + " INTEGER, " +
                    userStats.COL_GOLD + " INTEGER, " +
                    userStats.COL_USERNAME + " TEXT, " +
                    userStats.COL_COUNTRY + " TEXT," +
                    userStats.COL_CLICK_VALUE + " INTEGER," +
                    userStats.COL_MINER_VALUE + " INTEGER" + ");";

    public static final
    String DATABASE_LOAD =
            "SELECT * FROM " + userStats.TABLE_NAME + ";";


    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + userStats.TABLE_NAME);
        onCreate(db);
    }

    public void addHandler(UserModel user) {
        ContentValues values = new ContentValues();
        values.put(userStats.COL_FLOOR, user.getFloor());
        values.put(userStats.COL_CLICKS, user.getClickCount());
        values.put(userStats.COL_GOLD, user.getGold());
        values.put(userStats.COL_USERNAME, user.getUsername());
        values.put(userStats.COL_COUNTRY, user.getCountry());
        values.put(userStats.COL_CLICK_VALUE, user.getClickDamage());
        values.put(userStats.COL_MINER_VALUE, user.getDps());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(userStats.TABLE_NAME, null, values);
        db.close();
    }

    public String loadHandler() {
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DATABASE_LOAD, null);
        while(cursor.moveToNext()){
            int result_id = cursor.getInt(0);
            int result_floor = cursor.getInt(1);
            int result_clicks = cursor.getInt(2);
            int result_gold = cursor.getInt(3);
            String result_username = cursor.getString(4);
            String result_country = cursor.getString(5);
            int result_clickValue = cursor.getInt(6);
            int result_minerValue = cursor.getInt(7);
            result += String.valueOf(result_id) + " " + String.valueOf(result_floor) + " " +
                    String.valueOf(result_clicks) + " " + String.valueOf(result_gold) + " " +
                    result_username + " " + result_country + " " +
                    String.valueOf(result_clickValue) + " " + String.valueOf(result_minerValue) +
                    System.getProperty("line.separator");
        }

        cursor.close();
        db.close();
        return result;
    }
}