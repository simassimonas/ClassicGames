package com.example.simon.myapplicationpatipirma;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.Point;
import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


public class SnakeActivity extends AppCompatActivity {

    SnakeEngine snakeEngine;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        snakeEngine = new SnakeEngine(this, size);
        setContentView(snakeEngine);

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SnakeActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        snakeEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeEngine.pause();
    }

}