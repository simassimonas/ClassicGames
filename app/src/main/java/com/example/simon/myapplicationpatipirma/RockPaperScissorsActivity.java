package com.example.simon.myapplicationpatipirma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.Random;
import android.widget.Toast;
import android.widget.ImageButton;
import android.content.Intent;

public class RockPaperScissorsActivity extends AppCompatActivity {
    ImageButton btnBack;
    Button btnRock, btnScissors, btnPaper;
    ImageView ivComputerChoice, ivYourChoice;
    TextView tvScore;
    int YourScore=0, ComputerScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);

        tvScore = (TextView) findViewById(R.id.tvScore);

        btnPaper = (Button) findViewById(R.id.btnPaper);
        btnScissors = (Button) findViewById(R.id.btnScissors);
        btnRock = (Button) findViewById(R.id.btnRock);

        ivComputerChoice = (ImageView) findViewById(R.id.ivComputerChoice);
        ivYourChoice = (ImageView) findViewById(R.id.ivYourChoice);

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ivYourChoice.setImageResource(R.drawable.paperhuman);
                String message = play_turn("paper");
                Toast.makeText(RockPaperScissorsActivity.this, message, Toast.LENGTH_SHORT).show();
                tvScore.setText("YOU: " + Integer.toString(YourScore) + "   COMPUTER: " + Integer.toString(ComputerScore));
            }
         });

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ivYourChoice.setImageResource(R.drawable.rockhuman);
                String message = play_turn("rock");
                Toast.makeText(RockPaperScissorsActivity.this, message, Toast.LENGTH_SHORT).show();
                tvScore.setText("YOU: " + Integer.toString(YourScore) + "   COMPUTER: " + Integer.toString(ComputerScore));
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ivYourChoice.setImageResource(R.drawable.scissorshuman);
                String message = play_turn("scissors");
                Toast.makeText(RockPaperScissorsActivity.this, message, Toast.LENGTH_SHORT).show();
                tvScore.setText("YOU: " + Integer.toString(YourScore) + "   COMPUTER: " + Integer.toString(ComputerScore));
            }
        });

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RockPaperScissorsActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });

    }

    public String play_turn( String playerchoice){
        String computerchoice = "";
        Random r = new Random();
        int computerChoiceNumber = r.nextInt(3) + 1;

        if(computerChoiceNumber == 1 ) {
            computerchoice = "rock";
        } else

        if(computerChoiceNumber == 2 ) {
            computerchoice = "scissors";
        } else

        if(computerChoiceNumber == 3 ) {
            computerchoice = "paper";
        }

        //set the computer image based on his choice
        if(computerchoice == "rock" ){
            ivComputerChoice.setImageResource(R.drawable.rockpc);
        }else
        if(computerchoice =="scissors" ){
            ivComputerChoice.setImageResource(R.drawable.scissorspc);
        }else
        if(computerchoice == "paper" ){
            ivComputerChoice.setImageResource(R.drawable.paperpc);
        }

        //determine who won
        if(computerchoice == playerchoice ){
            return "Draw. ";
        }
        else if(playerchoice == "rock" && computerchoice == "scissors"){
            YourScore++;
            return "You Win";
        }
        else if(playerchoice == "rock" && computerchoice == "paper"){
            ComputerScore++;
            return "Computer Wins";
        }
        else if(playerchoice == "scissors" && computerchoice == "rock"){
            ComputerScore++;
            return "Computer Wins";
        }
        else if(playerchoice == "scissors" && computerchoice == "paper"){
            YourScore++;
            return "You Win";
        }
        else if(playerchoice == "paper" && computerchoice == "rock"){
            YourScore++;
            return "You Win";
        }
        else if(playerchoice == "paper" && computerchoice == "scissors"){
            ComputerScore++;
            return "Computer Wins";
        }
        else return "Nothing";
    }
}
