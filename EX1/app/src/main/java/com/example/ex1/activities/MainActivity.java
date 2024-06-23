package com.example.ex1.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex1.R;
import com.example.ex1.UIController;
import com.example.ex1.objects.GameManager;

public class MainActivity extends AppCompatActivity {
    private GameManager gameManager;
    private UIController uiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        ImageView life1 = findViewById(R.id.life1);
        ImageView life2 = findViewById(R.id.life2);
        ImageView life3 = findViewById(R.id.life3);
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);

        gameManager = new GameManager(this, gridLayout, life1, life2, life3);
        uiController = new UIController(this);

        uiController.setupButtons(leftButton, rightButton, gameManager);

        gameManager.startGame();
    }
}
