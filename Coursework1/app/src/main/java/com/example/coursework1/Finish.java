package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class Finish extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        //GET FINAL DATA FROM PAR3
        Intent intent = getIntent();

        //PAR3 data
        int player1par3 = intent.getIntExtra("player1par3", 0);
        int player2par3 = intent.getIntExtra("player2par3", 0);
        int player3par3 = intent.getIntExtra("player3par3", 0);
        int player4par3 = intent.getIntExtra("player4par3", 0);
        //Get player names
        String player1 = intent.getStringExtra("player1");
        String player2 = intent.getStringExtra("player2");
        String player3 = intent.getStringExtra("player3");
        String player4 = intent.getStringExtra("player4");
        //Get number of players
        int numofplayers = intent.getIntExtra("numofplayers", 1);
        //Get database current ID
        long GameID = intent.getLongExtra("GameID", 0);


        //SET DESIGN UP
        //PLAYER NAMES
        //Player1
        TextView player1name = (TextView) findViewById(R.id.player1text);
        player1name.setText(player1);
        //Player2
        TextView player2name = (TextView) findViewById(R.id.player2text);
        player2name.setText(player2);
        //Player3
        TextView player3name = (TextView) findViewById(R.id.player3text);
        player3name.setText(player3);
        //Player4
        TextView player4name = (TextView) findViewById(R.id.player4text);
        player4name.setText(player4);

        //SCORES
        TextView player1score = (TextView) findViewById(R.id.player1score);
        player1score.setText(String.valueOf(player1par3));
        //player2score
        TextView player2score = (TextView) findViewById(R.id.player2score);
        player2score.setText(String.valueOf(player2par3));
        //player3score
        TextView player3score = (TextView) findViewById(R.id.player3score);
        player3score.setText(String.valueOf(player3par3));
        //player4score
        TextView player4score = (TextView) findViewById(R.id.player4score);
        player4score.setText(String.valueOf(player4par3));

        //Hide player scoreboard based on how many players are playing
        if (numofplayers == 1) {
            //player2
            player2name.setVisibility(View.INVISIBLE);
            player2score.setVisibility(View.INVISIBLE);
            //player3
            player3name.setVisibility(View.INVISIBLE);
            player3score.setVisibility(View.INVISIBLE);
            //player4
            player4name.setVisibility(View.INVISIBLE);
            player4score.setVisibility(View.INVISIBLE);
        } else if (numofplayers == 2){
            player3name.setVisibility(View.INVISIBLE);
            player3score.setVisibility(View.INVISIBLE);
            player4name.setVisibility(View.INVISIBLE);
            player4score.setVisibility(View.INVISIBLE);
        } else if (numofplayers ==3){
            player4name.setVisibility(View.INVISIBLE);
            player4score.setVisibility(View.INVISIBLE);
        }




    }
    public void Home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}