package com.example.simon.myapplicationpatipirma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.EditText;
import java.util.Random;
import android.view.View;
import android.content.Intent;
import android.os.*;

public class MemoryGameActivity extends AppCompatActivity {

    TextView textNumber, tvLVL;
    EditText enterNumber;
    int Number, playerNumber, level=2;
    Button btnOk;
    ImageButton btnRR, btnBack;
    TextView tvAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        //generating the first level 1 random number
        generateNumber();

        //getting the answer(number) of the player
        enterNumber = (EditText) findViewById(R.id.enterNumber);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkin if there is any number written in the text field
                String input = enterNumber.getText().toString();
                if(input.isEmpty()){
                    enterNumber.setHint("Please enter the number");
                }
                else{
                    playerNumber= Integer.valueOf(enterNumber.getText().toString());
                    //checking if the answer is correct
                    isCorrcetNumber();
                }
            }
        });

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MemoryGameActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });

        //rr button
        btnRR = (ImageButton) findViewById(R.id.btnRR);
        btnRR.setVisibility(View.INVISIBLE);
        btnRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level=2;
                generateNumber();
                tvAnswer = (TextView) findViewById(R.id.tvAnswer);
                tvAnswer.setText("");
                btnRR.setVisibility(View.INVISIBLE);
                enterNumber.setHint("enter the number");
            }
        });

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MemoryGameActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });

    }

    public void generateNumber(){

        //showing level
        tvLVL = (TextView) findViewById(R.id.tvLVL);
        tvLVL.setText("LEVEL " + Integer.toString(level-1));

        //generating a random number
        Random r = new Random();
        int max=1, min=1;
        for(int i=1; i<level+1; i++){
            max=max*10;
        }
        for(int i=1; i<level; i++){
            min=min*10;
        }
        min+=1;
        Number = r.nextInt(max - min) + min;

        //showing that random number for a set amount of milisceonds
        textNumber = (TextView) findViewById(R.id.textNumber);
        textNumber.setVisibility(View.VISIBLE);
        textNumber.setText(Integer.toString(Number));
        textNumber.postDelayed(new Runnable() {
            public void run() {
                textNumber.setVisibility(View.INVISIBLE);
                btnOk.setVisibility(View.VISIBLE);
                enterNumber.setVisibility(View.VISIBLE);
            }
        }, 4000);

    }


    //determines whether the value of playerNumber is the same as the value of Number
    public void isCorrcetNumber(){
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        if(Number==playerNumber){
            level++;
            tvAnswer.setText("CORRECT ANSWER");
            enterNumber = (EditText) findViewById(R.id.enterNumber);
            enterNumber.setHint("enter the number");
            //makes the boxes invisible
            btnOk.setVisibility(View.INVISIBLE);
            enterNumber.setVisibility(View.INVISIBLE);
            //clears the text field
            enterNumber = (EditText) findViewById(R.id.enterNumber);
            enterNumber.getText().clear();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    tvAnswer.setText("");
                    generateNumber();
                }

            }, 3000); // 5000ms delay

        }
        else{
            tvAnswer.setText("INCORRECT ANSWER");
            //makes the boxes invisible
            btnOk.setVisibility(View.INVISIBLE);
            enterNumber.setVisibility(View.INVISIBLE);
            //clears the text field
            enterNumber = (EditText) findViewById(R.id.enterNumber);
            enterNumber.getText().clear();
            btnRR = (ImageButton) findViewById(R.id.btnRR);
            btnRR.setVisibility(View.VISIBLE);
        }

    }

}
