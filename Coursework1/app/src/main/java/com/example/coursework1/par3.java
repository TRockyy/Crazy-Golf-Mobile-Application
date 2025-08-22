package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class par3 extends AppCompatActivity {
    int player1par3,player2par3,player3par3,player4par3 = 0;

    //Store the player scores as STRING so they can be converted to Integer from EditText
    String player1score,player2score,player3score,player4score = "0";

    //PAR3 button declaration
    Button finish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par3);

        //GET DATA FROM PAR1
        Intent intent = getIntent();
        int player1par2 = intent.getIntExtra("player1par2", 0);
        int player2par2 = intent.getIntExtra("player2par2", 0);
        int player3par2 = intent.getIntExtra("player3par2", 0);
        int player4par2 = intent.getIntExtra("player4par2", 0);
        Log.d("par2", "VALUE IS - " + player1par2);

        //=========================================================================

        //Datebase
        Database myDb = new Database(this);

        //Get player names & SQL database ID number
        String player1 = intent.getStringExtra("player1");
        String player2 = intent.getStringExtra("player2");
        String player3 = intent.getStringExtra("player3");
        String player4 = intent.getStringExtra("player4");
        int numofplayers = intent.getIntExtra("numofplayers", 1);
        long GameID = intent.getLongExtra("GameID", 0);

        //Validation, error message - Set invisible until needs to be displayed
        TextView error = (TextView) findViewById(R.id.errortextt);
        error.setVisibility(View.INVISIBLE);

        //Player Names - Change the text views to player names
        TextView playertext1 = (TextView) findViewById(R.id.playertext1);
        playertext1.setText(player1);

        TextView playertext2 = (TextView) findViewById(R.id.playertext2);
        playertext2.setText(player2);

        TextView playertext3 = (TextView) findViewById(R.id.playertext3);
        playertext3.setText(player3);

        TextView playertext4 = (TextView) findViewById(R.id.playertext4);
        playertext4.setText(player4);

        TextView num = (TextView) findViewById(R.id.numofplayertest);
        num.setText(String.valueOf(numofplayers));


        //Scores - Set EditTexts as the input for score values
        EditText score1 = findViewById(R.id.score1);
        EditText score2 = findViewById(R.id.score2);
        EditText score3 = findViewById(R.id.score3);
        EditText score4 = findViewById(R.id.score4);


        //Hide score inputs based on how many players are playing
        if (numofplayers == 1) {
            score2.setVisibility(View.INVISIBLE);
            score3.setVisibility(View.INVISIBLE);
            score4.setVisibility(View.INVISIBLE);
        } else if (numofplayers == 2){
            score3.setVisibility(View.INVISIBLE);
            score4.setVisibility(View.INVISIBLE);
        } else if (numofplayers ==3){
            score4.setVisibility(View.INVISIBLE);
        }

        //Set a OnClick listener for PAR2 button, when clicked:
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store par1 scores and convert to integer from string input, CHECK IF ITS A STRING IF NOT THEN GIVE ERROR
                player1score = score1.getText().toString();
                //For each string to integer conversion, VALIDATE that the user hasn't entered a string e.g. "Food" instead of a number as this would CRASH
                try {
                    player1par3 = Integer.parseInt(player1score);
                }catch (NumberFormatException e) {
                    error.setVisibility(View.VISIBLE);
                }
                player2score = score2.getText().toString();
                try {
                    player2par3 = Integer.parseInt(player2score);
                }catch (NumberFormatException e) {
                    error.setVisibility(View.VISIBLE);
                }
                player3score = score3.getText().toString();
                try {
                    player3par3 = Integer.parseInt(player3score);
                }catch (NumberFormatException e) {
                    error.setVisibility(View.VISIBLE);
                }
                player4score = score4.getText().toString();
                try {
                    player4par3 = Integer.parseInt(player4score);
                }catch (NumberFormatException e) {
                    error.setVisibility(View.VISIBLE);
                }

                //Validation for if the first players value is higher than 0, meaning no input = Show error message
                if (player1par3 == 0){
                    error.setVisibility(View.VISIBLE);
                } else {
                    //If VALIDATED inputs are fine, go to FINISH and send data
                    player1par3 = player1par2 + player1par3;
                    player2par3 = player2par2 + player2par3;
                    player3par3 = player3par2 + player3par3;
                    player4par3 = player4par2 + player4par3;
                    Log.d("par3", "VALUE IS - " + player1par3);
                    myDb.updatescores(GameID, player1par3,player2par3,player3par3,player4par3);


                    Intent intent = new Intent(par3.this, Finish.class);
                    intent.putExtra("GameID", GameID);
                    intent.putExtra("player1par3", player1par3);
                    intent.putExtra("player2par3", player2par3);
                    intent.putExtra("player3par3", player3par3);
                    intent.putExtra("player4par3", player4par3);
                    intent.putExtra("player1", player1);
                    intent.putExtra("player2", player2);
                    intent.putExtra("player3", player3);
                    intent.putExtra("player4", player4);
                    intent.putExtra("GameID", GameID);
                    intent.putExtra("numofplayers", numofplayers);
                    startActivity(intent);
                }
            }
        })
        ;}}

