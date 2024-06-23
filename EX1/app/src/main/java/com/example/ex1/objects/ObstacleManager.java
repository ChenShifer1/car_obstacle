package com.example.ex1.objects;

import android.os.Handler;

import com.example.ex1.R;

import java.util.Random;

public class ObstacleManager {
    private Handler handler = new Handler();
    private Random random = new Random();
    private GridManager gridManager;
    private GameManager gameManager;
    private boolean running = true;

    public ObstacleManager() {

    }

    ;

    public ObstacleManager(GridManager gridManager, GameManager gameManager) {
        this.gridManager = gridManager;
        this.gameManager = gameManager;
    }

    public void startObstacles() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    dropObstacle();
                    handler.postDelayed(this, 1000); // Drop obstacle every second
                }
            }
        }, 1000);
    }

    public void stopObstacles() {
        running = false;
    }

    private void dropObstacle() {
        int col = random.nextInt(3);
        moveObstacleDown(col, 0);
    }

    private void moveObstacleDown(int col, int row) {
        if (row > 0) {
            gridManager.clearPosition(col, row - 1);
        }
        if (row < 3) {
            if (row == gameManager.getCar().getPositionY() && col == gameManager.getCar().getPositionX()) {
                gameManager.handleCollision();
            } else {
                gridManager.setObstacle(col, row, R.drawable.obstacle);
                handler.postDelayed(() -> moveObstacleDown(col, row + 1), 1000);
            }
        } else {
            gridManager.clearPosition(col, row - 1);
        }
    }
}
