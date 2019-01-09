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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        snakeEngine = new SnakeEngine(this, size);
        setContentView(snakeEngine);

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