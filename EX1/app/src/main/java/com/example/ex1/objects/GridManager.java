package com.example.ex1.objects;

import android.widget.ImageView;
import android.widget.GridLayout;
import android.content.Context;

import com.example.ex1.R;

public class GridManager {
    private ImageView[][] grid = new ImageView[3][3];

    public GridManager() {

    }

    ;

    public GridManager(GridLayout gridLayout) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = (ImageView) gridLayout.getChildAt(3 * i + j);
                grid[i][j].setTag(null);
            }
        }
    }

    public void setCarPosition(int x, int y, int drawableId) {
        grid[y][x].setImageResource(drawableId);
        grid[y][x].setTag("car");
    }

    public void clearPosition(int x, int y) {
        grid[y][x].setImageResource(R.drawable.transparent);
        grid[y][x].setTag(null);
    }

    public boolean canMoveTo(int x, int y) {
        return grid[y][x].getTag() == null;
    }

    public void setObstacle(int x, int y, int drawableId) {
        grid[y][x].setImageResource(drawableId);
        grid[y][x].setTag("obstacle");
    }
}
