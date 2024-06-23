package com.example.ex1.objects;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex1.R;

public class GameManager {
    private GridManager gridManager;
    private Car car;
    private ObstacleManager obstacleManager;
    private LivesManager livesManager;
    private AppCompatActivity activity;
    private boolean gameRunning;

    public GameManager(AppCompatActivity activity, GridLayout gridLayout, ImageView life1, ImageView life2, ImageView life3) {
        this.activity = activity;
        gridManager = new GridManager(gridLayout);
        car = new Car();
        livesManager = new LivesManager(life1, life2, life3);
        obstacleManager = new ObstacleManager(gridManager, this);
        gameRunning = true;
    }

    public GameManager() {

    }

    ;

    public void startGame() {
        gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);
        obstacleManager.startObstacles();
    }

    public void endGame() {
        gameRunning = false;
        obstacleManager.stopObstacles();
        Toast.makeText(activity, "Game Over!", Toast.LENGTH_LONG).show();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public GridManager getGridManager() {
        return gridManager;
    }

    public Car getCar() {
        return car;
    }

    public LivesManager getLivesManager() {
        return livesManager;
    }

    public void moveCar(int direction) {
        int newX = car.getPositionX() + direction;
        if (newX >= 0 && newX < 3 && gridManager.canMoveTo(newX, car.getPositionY())) {
            gridManager.clearPosition(car.getPositionX(), car.getPositionY());
            car.setPositionX(newX);
            gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);
        }
    }

    public void handleCollision() {
        livesManager.loseLife();
        if (livesManager.getLivesCount() == 0) {
            endGame();
        } else {
            resetCarPosition();
        }
    }

    private void resetCarPosition() {
        gridManager.clearPosition(car.getPositionX(), car.getPositionY());
        car.setPositionX(1); // Reset to center
        car.setPositionY(2); // Reset to bottom
        gridManager.setCarPosition(car.getPositionX(), car.getPositionY(), R.drawable.car);
    }
}
