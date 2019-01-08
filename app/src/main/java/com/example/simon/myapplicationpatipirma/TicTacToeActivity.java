/*package com.example.simon.myapplicationpatipirma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeActivity extends AppCompatActivity {
    int c[][];
    int i, j, k = 0;
    Button b[][];
    TextView textView;
    AI ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    }
}*/

package com.example.simon.myapplicationpatipirma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.os.Vibrator;


public class TicTacToeActivity extends AppCompatActivity  {

    ImageButton btnBack, btnRR;
    int c[][];
    int i, j, k = 0;
    ImageButton b[][];
    TextView textView;
    AI ai;


    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TicTacToeActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });

        //rr button
        btnRR = (ImageButton) findViewById(R.id.btnRR);
        btnRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBoard();
                b[1][1].setImageResource(R.drawable.t3);
                b[1][2].setImageResource(R.drawable.t3);
                b[1][3].setImageResource(R.drawable.t3);
                b[2][1].setImageResource(R.drawable.t3);
                b[2][2].setImageResource(R.drawable.t3);
                b[2][3].setImageResource(R.drawable.t3);
                b[3][1].setImageResource(R.drawable.t3);
                b[3][2].setImageResource(R.drawable.t3);
                b[3][3].setImageResource(R.drawable.t3);

            }
        });

        setBoard();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("New Game");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        setBoard();
        return true;
    }*/

    //0 is for nulls, 1 is for x and 2 is for none.
    // Set up the game board.
    private void setBoard() {
        ai = new AI();
        b = new ImageButton[4][4];
        c = new int[4][4];


        textView = (TextView) findViewById(R.id.dialogue);


        b[1][3] = (ImageButton) findViewById(R.id.one);
        b[1][2] = (ImageButton) findViewById(R.id.two);
        b[1][1] = (ImageButton) findViewById(R.id.three);


        b[2][3] = (ImageButton) findViewById(R.id.four);
        b[2][2] = (ImageButton) findViewById(R.id.five);
        b[2][1] = (ImageButton) findViewById(R.id.six);


        b[3][3] = (ImageButton) findViewById(R.id.seven);
        b[3][2] = (ImageButton) findViewById(R.id.eight);
        b[3][1] = (ImageButton) findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }


        textView.setText("YOUR TURN");


        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isClickable()) {
                    b[i][j].setImageResource(R.drawable.t3);
                    b[i][j].setClickable(true);
                }
            }
        }
    }


    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isClickable()) {
                b[x][y].setClickable(false);
                b[x][y].setImageResource(R.drawable.t2);
                c[x][y] = 0;
                textView.setText("");
                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }


    private class AI {
        public void takeTurn() {
            if(c[1][1]==2 &&
                    ((c[1][2]==0 && c[1][3]==0) ||
                            (c[2][2]==0 && c[3][3]==0) ||
                            (c[2][1]==0 && c[3][1]==0))) {
                markSquare(1,1);
            } else if (c[1][2]==2 &&
                    ((c[2][2]==0 && c[3][2]==0) ||
                            (c[1][1]==0 && c[1][3]==0))) {
                markSquare(1,2);
            } else if(c[1][3]==2 &&
                    ((c[1][1]==0 && c[1][2]==0) ||
                            (c[3][1]==0 && c[2][2]==0) ||
                            (c[2][3]==0 && c[3][3]==0))) {
                markSquare(1,3);
            } else if(c[2][1]==2 &&
                    ((c[2][2]==0 && c[2][3]==0) ||
                            (c[1][1]==0 && c[3][1]==0))){
                markSquare(2,1);
            } else if(c[2][2]==2 &&
                    ((c[1][1]==0 && c[3][3]==0) ||
                            (c[1][2]==0 && c[3][2]==0) ||
                            (c[3][1]==0 && c[1][3]==0) ||
                            (c[2][1]==0 && c[2][3]==0))) {
                markSquare(2,2);
            } else if(c[2][3]==2 &&
                    ((c[2][1]==0 && c[2][2]==0) ||
                            (c[1][3]==0 && c[3][3]==0))) {
                markSquare(2,3);
            } else if(c[3][1]==2 &&
                    ((c[1][1]==0 && c[2][1]==0) ||
                            (c[3][2]==0 && c[3][3]==0) ||
                            (c[2][2]==0 && c[1][3]==0))){
                markSquare(3,1);
            } else if(c[3][2]==2 &&
                    ((c[1][2]==0 && c[2][2]==0) ||
                            (c[3][1]==0 && c[3][3]==0))) {
                markSquare(3,2);
            }else if( c[3][3]==2 &&
                    ((c[1][1]==0 && c[2][2]==0) ||
                            (c[1][3]==0 && c[2][3]==0) ||
                            (c[3][1]==0 && c[3][2]==0))) {
                markSquare(3,3);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while(a==0 || b==0 || c[a][b]!=2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }
                markSquare(a,b);
            }
        }


        private void markSquare(int x, int y) {
            b[x][y].setClickable(false);
            b[x][y].setImageResource(R.drawable.t1);
            c[x][y] = 1;
            checkBoard();
        }
    }


    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textView.setText("GAME OVER. YOU WIN!");
            //vibration
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(600);
            gameOver = true;
            makeUnclicable();
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("GAME OVER. YOU LOSE!");
            //vibration
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(600);
            makeUnclicable();
            gameOver = true;
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                textView.setText("GAME OVER. DRAW!");
                //vibration
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(600);
            }
        }
        return gameOver;
    }

    //makes all the buttons unclicable
    public void makeUnclicable(){
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                    b[i][j].setClickable(false);
            }
        }
    }

}

