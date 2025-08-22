package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class StartGame extends AppCompatActivity {
Database myDb;
String player1,player2,player3,player4;
int numofplayers = 0;
    EditText player1input;
    EditText player2input;
    EditText player3input;
    EditText player4input;

    Button submitname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        myDb = new Database(this);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textdate);
        textViewDate.setText(currentDate);
        player1input = (EditText) findViewById(R.id.player1);
        player2input = (EditText) findViewById(R.id.player2);
        player3input = (EditText) findViewById(R.id.player3);
        player4input = (EditText) findViewById(R.id.player4);

        submitname = (Button) findViewById(R.id.submitname);
        submitname.setOnClickListener(new View.OnClickListener() {

            //When Button is clicked
            @Override
            public void onClick(View view) {
                showConfirmationDialog();

            }
        });
}
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CONFIRM NAMES");
        builder.setMessage("Are the names correct?");
        //YES
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                TextView textViewDate = findViewById(R.id.textdate);
                textViewDate.setText(currentDate);
                player1 = player1input.getText().toString();
                player2 = player2input.getText().toString();
                player3 = player3input.getText().toString();
                player4 = player4input.getText().toString();
                TextView error = findViewById(R.id.errortext);

                //validation
                if (player1.isEmpty() && player2.isEmpty() && player3.isEmpty() && player4.isEmpty()) {
                    numofplayers = 0;

                    error.setText("Error - No Players Entered");
                }
                else if (player2.isEmpty() && player3.isEmpty() && player4.isEmpty()) {
                    numofplayers = 1;
                }
                else if (player3.isEmpty() && player4.isEmpty()) {
                    numofplayers = 2;
                }
                else if (player4.isEmpty()) {
                    numofplayers = 3;
                }
                else {
                    numofplayers = 4;
                }

                if (numofplayers == 0) {
                    error.setText("Error - No Players Entered");
                }
                else {
                    //Insert the player names into the database and get the current ID being used
                    long lastInsertedId = myDb.insertdata(player1, player2, player3, player4, currentDate);
                    long GameID = lastInsertedId;
                    //SEND DATA TO PAR 1
                    Intent intent = new Intent(StartGame.this, Holes.class);
                    intent.putExtra("player1", player1);
                    intent.putExtra("player2", player2);
                    intent.putExtra("player3", player3);
                    intent.putExtra("player4", player4);
                    intent.putExtra("GameID", GameID);
                    intent.putExtra("numofplayers", numofplayers);
                    startActivity(intent);
                }

            }
        });
        //NO
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
