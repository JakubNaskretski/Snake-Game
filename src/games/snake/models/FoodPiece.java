package games.snake.models;

import java.awt.*;
import java.util.ArrayList;

public class FoodPiece {

    int x;
    int y;
    int width;
    int height;
    Color color;
    Rectangle foodPiece;
    int[] foodCoordinates;

    public FoodPiece(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.foodCoordinates = new int[]{x, y, width, height};
        this.color = Color.red;
        foodPiece = null;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getFoodCoordinates() {
        return foodCoordinates;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getFoodPiece() {
        return foodPiece;
    }

    public void setFoodPiece(Rectangle foodPiece) {
        this.foodPiece = foodPiece;
    }

    public void setFoodCoordinates(int[] foodCoordinates) {
        this.foodCoordinates = foodCoordinates;
    }
}
