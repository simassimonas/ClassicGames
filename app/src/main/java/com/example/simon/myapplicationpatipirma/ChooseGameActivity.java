package com.example.simon.myapplicationpatipirma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseGameActivity extends AppCompatActivity {
    ImageButton btnToRPSGame, btnToTicTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        btnToRPSGame = (ImageButton) findViewById(R.id.btnRPSGame);
        btnToRPSGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseGameActivity.this,RockPaperScissorsActivity.class);
                startActivity(i);
            }
        });

        btnToTicTacToeGame = (ImageButton) findViewById(R.id.btnTicTacToeGame);
        btnToTicTacToeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseGameActivity.this,TicTacToeActivity.class);
                startActivity(i);
            }
        });


    }
}
