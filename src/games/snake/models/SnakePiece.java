package games.snake.models;

import java.awt.*;

public class SnakePiece {

    final int x_snake;
    final int y_snake;
    final int width_snake;
    final int height_snake;
    final Color color;

    public SnakePiece(int x_snake, int y_snake, int width_snake, int height_snake, Color color) {
        this.x_snake = x_snake;
        this.y_snake = y_snake;
        this.width_snake = width_snake;
        this.height_snake = height_snake;
        this.color = color;
    }
}