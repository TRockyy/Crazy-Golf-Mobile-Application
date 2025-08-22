package com.example.coursework1;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;


public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "gamesmk3";
    private static final int DB_VERSION = 1;

    public Database(Context context) { // Constructor.
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE History ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DATE TEXT, "
                + "NAME1 TEXT, "
                + "NAME2 TEXT, "
                + "NAME3 TEXT, "
                + "NAME4 TEXT, "
                + "SCORE1 INTEGER, "
                + "SCORE2 INTEGER, "
                + "SCORE3 INTEGER, "
                + "SCORE4 INTEGER);"
        );
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_NAME);
        onCreate(db);
    }

    public long insertdata(String player1,String player2,String player3,String player4, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME1", player1);
        contentValues.put("NAME2", player2);
        contentValues.put("NAME3", player3);
        contentValues.put("NAME4", player4);
        contentValues.put("DATE", date);
        long lastInsertedId = db.insert("History",null, contentValues);
            return lastInsertedId;
    }

    public void updatescores(long id, int scorevalue1, int scorevalue2, int scorevalue3, int scorevalue4) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SCORE1", +scorevalue1);
        contentValues.put("SCORE2", +scorevalue2);
        contentValues.put("SCORE3", +scorevalue3);
        contentValues.put("SCORE4", +scorevalue4);
        String[] location = {String.valueOf(id)};
        db.update("History", contentValues, "_id" + " = ?", location);
    }

    public Cursor displaydata() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM "+"History" + " ORDER BY " + "_id" + " DESC ", null);
            return cursor;
            }


    public String getHighest() {

        SQLiteDatabase db = this.getReadableDatabase();
        int highestscorevalue1 = 0;
        int highestscorevalue2 = 0;
        int highestscorevalue3 = 0;
        int highestscorevalue4 = 0;
        String name1 = null;
        String name2 = null;
        String name3 = null;
        String name4 = null;
        String nameholder = null;
        int currenthighest = 0;


        //Query
        String scorequery1 = "SELECT NAME1, MIN(score1) FROM "+"History";
        String scorequery2 = "SELECT NAME2, MIN(score2) FROM "+"History";
        String scorequery3 = "SELECT NAME3, MIN(score3) FROM "+"History";
        String scorequery4 = "SELECT NAME4, MIN(score4) FROM "+"History";
        Cursor cursor1 = db.rawQuery(scorequery1, null);
        Cursor cursor2 = db.rawQuery(scorequery2, null);
        Cursor cursor3 = db.rawQuery(scorequery3, null);
        Cursor cursor4 = db.rawQuery(scorequery4, null);

        //Get highscore1
        if (cursor1 != null && cursor1.moveToFirst()) {
            highestscorevalue1 = cursor1.getInt(1);
            name1 = cursor1.getString(0);
            cursor1.close();
            Log.d("nametest", "name is - " + name1);
        }
        //Get highscore2
        if (cursor2 != null && cursor2.moveToFirst()) {
            highestscorevalue2 = cursor2.getInt(1);
            name2 = cursor2.getString(0);
            cursor2.close();
        }
        //Get highscore3
        if (cursor3 != null && cursor3.moveToFirst()) {
            highestscorevalue3 = cursor3.getInt(1);
            name3 = cursor3.getString(0);
            cursor3.close();
        }
        //Get highscore4
        if (cursor4 != null && cursor4.moveToFirst()) {
            highestscorevalue4 = cursor4.getInt(1);
            name4 = cursor4.getString(0);
            cursor4.close();
        }

        //Highest is now the value of highscore1
        currenthighest = highestscorevalue1;
        nameholder = name1;


        //Find which is highest out of all 4 scores by comparing them
        //E.g. = If value2 is higher than already stored value 1 -  THEN value2 is higher and made the currenthighest
        if (highestscorevalue2 > currenthighest) {
            currenthighest = highestscorevalue2;
            nameholder = name2;
        }
        if (highestscorevalue3 > currenthighest) {
            currenthighest = highestscorevalue3;
            nameholder = name3;
        }
        if (highestscorevalue4 > currenthighest) {
            currenthighest = highestscorevalue4;
            nameholder = name4;
        }

        String scorename = nameholder + "\uD83D\uDD25" + String.valueOf(currenthighest);


        db.close();
        return scorename;

        }
    }

