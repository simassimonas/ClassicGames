package com.example.simon.myapplicationpatipirma;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

class SnakeEngine extends SurfaceView implements Runnable {
    private Thread thread = null;
    private Context context;

    private int eat_bob = -1;
    private int snake_crash = -1;

    public enum Heading {UP, RIGHT, DOWN, LEFT}

    private Heading heading = Heading.RIGHT;

    private int screenX;
    private int screenY;

    private int snakeLength;

    private int bobX;
    private int bobY;

    private int blockSize;

    private final int BLOCKS_WIDE = 24;
    private final int BLOCKS_HIGH = 36;

    private long nextFrameTime;

    private final long FPS = 10;

    private final long MILLIS_PER_SECOND = 1000;

    private int score;
    private int[] snakeXs;
    private int[] snakeYs;

    private volatile boolean isPlaying;

    private Canvas canvas;

    private SurfaceHolder surfaceHolder;
    private Paint paint;


    public SnakeEngine(Context context, Point size) {
        super(context);

        screenX = size.x;
        screenY = size.y;

        blockSize = screenX / BLOCKS_WIDE / 6 * 5;

        // BLOCKS_HIGH = screenY / blockSize;


        surfaceHolder = getHolder();
        paint = new Paint();

        snakeXs = new int[200];
        snakeYs = new int[200];

        newGame();
    }

    @Override
    public void run() {

        while (isPlaying) {

            if (updateRequired()) {
                update();
                draw();
            }
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void newGame() {
        snakeLength = 3;
        snakeXs[0] = BLOCKS_WIDE / 2;
        snakeYs[0] = BLOCKS_HIGH / 2;

        snakeXs[1] = BLOCKS_WIDE / 2 - 1;
        snakeYs[1] = BLOCKS_HIGH / 2;

        snakeXs[2] = BLOCKS_WIDE / 2 - 2;
        snakeYs[2] = BLOCKS_HIGH / 2;

        spawnBob();
        score = 0;

        nextFrameTime = System.currentTimeMillis();
    }

    public void spawnBob() {
        int x, y;
        boolean z = false;
        Random random = new Random();
        do {
            x = random.nextInt(BLOCKS_WIDE - 1) + 1;
            y = random.nextInt(BLOCKS_HIGH - 1) + 1;

            for (int i = 0; i < snakeLength; i++) {
                if ((x == snakeXs[i]) && (y == snakeYs[i])) break;
                if (i == snakeLength - 1) z = true;
            }
        } while (!z);//suks, kol nesugeneruos bob nesancio ant snake
        bobX = x;
        bobY = y;
    }

    private void eatBob() {
        snakeLength++;
        spawnBob();
        score++;//tis might be a mistake though

    }

    private void moveSnake() {
        for (int i = snakeLength; i > 0; i--) {

            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];
        }
        switch (heading) {
            case UP://juda ir pereina per sienas
                snakeYs[0]--;
                if (snakeYs[0] < 0) snakeYs[0] = BLOCKS_HIGH - 1;
                break;
            case RIGHT:
                snakeXs[0]++;
                if (snakeXs[0] > BLOCKS_WIDE - 1) snakeXs[0] = 0;
                break;
            case DOWN:
                snakeYs[0]++;
                if (snakeYs[0] > BLOCKS_HIGH - 1) snakeYs[0] = 0;
                break;
            case LEFT:
                snakeXs[0]--;
                if (snakeXs[0] < 0) snakeXs[0] = BLOCKS_WIDE - 1;
                break;
        }
    }

    private boolean detectDeath() {
        boolean dead = false;

        for (int i = 1; i < snakeLength; i++) {
            if ((snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                dead = true;
            }
        }
        return dead;
    }

    public void update() {
        if (snakeXs[0] == bobX && snakeYs[0] == bobY) {
            eatBob();
        }
        moveSnake();

        if (detectDeath()) {

            newGame();
        }
    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            paint.setTextSize(30);
            canvas.drawText("Score:" + score, 10, 30, paint);
            //     canvas.drawText("X: " +snakeXs[0],10,60,paint);
            //    canvas.drawText("Y: " +snakeYs[0],10,90,paint);

            for (int i = 0; i < snakeLength; i++) {
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }
            paint.setColor(Color.argb(255, 255, 255, 255));

            canvas.drawRect(bobX * blockSize,
                    (bobY * blockSize),
                    (bobX * blockSize) + blockSize,
                    (bobY * blockSize) + blockSize,
                    paint);

            paint.setColor(Color.argb(255, 244, 223, 24));
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean updateRequired() {

        if (nextFrameTime <= System.currentTimeMillis()) {
            nextFrameTime = System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;
            return true;

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionEvent.getX() >= screenX / 2) {
                    switch (heading) {
                        case UP:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.UP;
                            break;
                    }
                } else {
                    switch (heading) {
                        case UP:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.UP;
                            break;
                    }
                }
        }
        return true;
    }

}