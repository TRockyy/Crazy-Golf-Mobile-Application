package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;
import android.adservices.topics.GetTopicsRequest;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class History extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Database mydb = new Database(this);

        TextView textView = findViewById(R.id.textViewtest);

        Cursor cursor = mydb.displaydata();

        StringBuilder Displayhistory = new StringBuilder();

        while (cursor.moveToNext()) {
            Displayhistory.append("\nDATE\uD83D\uDCC5  " +cursor.getString(1)
            +"\nNAMES\uD83D\uDC64 " + cursor.getString(2)
            + " - "  + cursor.getString(3)
                    + " - "  + cursor.getString(4)
                    + " - "  + cursor.getString(5)
                    + "\nSCORES\uD83C\uDFAF  "  + cursor.getString(6)
                    + " - "+ cursor.getString(7)
                    + " - "+ cursor.getString(8)
                    + " - "+ cursor.getString(9)
                    + "\n\n");



        }

        textView.setText(Displayhistory);

    }
}