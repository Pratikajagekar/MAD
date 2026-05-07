package com.tythen.tysnake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.tythen.tysnake.Constant.*;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private String direction = "right";

    private int foodX = 0;
    private int foodY = 0;

    private int score = 0;

    private TextView tv_score;

    private List<SnakePoint> snakePoints = new ArrayList<>();

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private Timer timer;

    private Canvas canvas = null;

    private Paint pointColor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        surfaceView = findViewById(R.id.sv_game);
        surfaceView.getHolder().addCallback(this);

        tv_score = findViewById(R.id.tv_score);

        Button btn_up = findViewById(R.id.btn_up);
        Button btn_right = findViewById(R.id.btn_right);
        Button btn_left = findViewById(R.id.btn_left);
        Button btn_down = findViewById(R.id.btn_down);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!direction.equals("down")) {
                    direction = "up";
                }
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!direction.equals("left")) {
                    direction = "right";
                }
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!direction.equals("right")) {
                    direction = "left";
                }
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!direction.equals("up")) {
                    direction = "down";
                }
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        this.surfaceHolder = holder;

        init();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    // Initialize Game
    private void init() {

        snakePoints.clear();

        score = 0;

        tv_score.setText("0");

        direction = "right";

        int startX = 3 * pointSize;

        for (int i = 0; i < defaultTablePoints; i++) {

            SnakePoint snakePoint = new SnakePoint(startX, pointSize);

            snakePoints.add(snakePoint);

            startX -= 2 * pointSize;
        }

        addPoint();

        moveSnake();
    }

    // Create Food
    private void addPoint() {

        int newFoodX = new Random().nextInt(
                (surfaceView.getWidth() - 2 * pointSize) / pointSize
        );

        int newFoodY = new Random().nextInt(
                (surfaceView.getHeight() - 2 * pointSize) / pointSize
        );

        if (newFoodX % 2 != 0) {
            newFoodX++;
        }

        if (newFoodY % 2 != 0) {
            newFoodY++;
        }

        foodX = (newFoodX * pointSize) + pointSize;

        foodY = (newFoodY * pointSize) + pointSize;
    }

    // Snake Movement
    private void moveSnake() {

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                int headPositionX = snakePoints.get(0).getPositionX();

                int headPositionY = snakePoints.get(0).getPositionY();

                // Check Food Collision
                if (foodX == headPositionX && foodY == headPositionY) {

                    growSnake();

                    addPoint();
                }

                // Move Snake
                switch (direction) {

                    case "right":

                        snakePoints.get(0).setPositionX(headPositionX + pointSize * 2);
                        snakePoints.get(0).setPositionY(headPositionY);

                        break;

                    case "left":

                        snakePoints.get(0).setPositionX(headPositionX - pointSize * 2);
                        snakePoints.get(0).setPositionY(headPositionY);

                        break;

                    case "up":

                        snakePoints.get(0).setPositionX(headPositionX);
                        snakePoints.get(0).setPositionY(headPositionY - pointSize * 2);

                        break;

                    case "down":

                        snakePoints.get(0).setPositionX(headPositionX);
                        snakePoints.get(0).setPositionY(headPositionY + pointSize * 2);

                        break;
                }

                // Check Game Over
                if (checkGameOver(headPositionX, headPositionY)) {

                    timer.purge();

                    timer.cancel();

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(GameActivity.this);

                    builder.setTitle("Game Over");

                    builder.setCancelable(false);

                    builder.setMessage("Your Score: " + score);

                    saveScore();

                    builder.setNegativeButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    startActivity(
                                            new Intent(GameActivity.this,
                                                    MainActivity.class)
                                    );
                                }
                            });

                    builder.setPositiveButton("Restart",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    init();
                                }
                            });

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            builder.show();
                        }
                    });

                    return;
                }

                // Draw Game
                canvas = surfaceHolder.lockCanvas();

                canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);

                // Draw Snake Head
                int X = snakePoints.get(0).getPositionX();

                int Y = snakePoints.get(0).getPositionY();

                canvas.drawCircle(X, Y, pointSize, createPointColor());

                // Draw Food
                canvas.drawCircle(foodX, foodY, pointSize, createPointColor());

                // Draw Snake Body
                for (int i = 1; i < snakePoints.size(); i++) {

                    int tempX = snakePoints.get(i).getPositionX();

                    int tempY = snakePoints.get(i).getPositionY();

                    snakePoints.get(i).setPositionX(headPositionX);

                    snakePoints.get(i).setPositionY(headPositionY);

                    canvas.drawCircle(
                            snakePoints.get(i).getPositionX(),
                            snakePoints.get(i).getPositionY(),
                            pointSize,
                            createPointColor()
                    );

                    headPositionX = tempX;

                    headPositionY = tempY;
                }

                surfaceHolder.unlockCanvasAndPost(canvas);
            }

        }, 1000 - snakeMovingSpeed, 1000 - snakeMovingSpeed);
    }

    // Save Score
    private void saveScore() {

        SharedPreferences shared =
                getSharedPreferences("score", Context.MODE_PRIVATE);

        int total = shared.getInt("total", 0);

        ++total;

        SharedPreferences.Editor editor = shared.edit();

        Date date = new Date(System.currentTimeMillis());

        String nowDate =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        editor.putString(String.valueOf(total + "date"), nowDate);

        editor.putInt(String.valueOf(total + "score"), score);

        editor.putInt("total", total);

        editor.apply();
    }

    // Grow Snake
    private void growSnake() {

        snakePoints.add(new SnakePoint(0, 0));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                score++;

                tv_score.setText(String.valueOf(score));
            }
        });
    }

    // Check Game Over
    private boolean checkGameOver(int headPositionX, int headPositionY) {

        boolean gameOver = false;

        if (snakePoints.get(0).getPositionX() < 0 ||
                snakePoints.get(0).getPositionX() > surfaceView.getWidth() ||
                snakePoints.get(0).getPositionY() < 0 ||
                snakePoints.get(0).getPositionY() > surfaceView.getHeight()) {

            gameOver = true;
        }

        for (int i = 1; i < snakePoints.size(); i++) {

            if (snakePoints.get(i).getPositionX() == headPositionX &&
                    snakePoints.get(i).getPositionY() == headPositionY) {

                gameOver = true;
            }
        }

        return gameOver;
    }

    // Snake Color
    @SuppressLint("ResourceAsColor")
    private Paint createPointColor() {

        if (pointColor == null) {

            pointColor = new Paint();

            pointColor.setColor(Color.GREEN);

            pointColor.setStyle(Paint.Style.FILL);

            pointColor.setAntiAlias(true);
        }

        return pointColor;
    }
}