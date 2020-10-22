package games.snake.controllers;

import games.snake.models.FoodPiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DrawFoodPiece extends JComponent {

    FoodPiece foodPiece;

    public DrawFoodPiece() {
        this.foodPiece = new FoodPiece(150,150,20,20);
        repaint();
    }

    public void createNewFoodPiece(int x, int y){
        foodPiece.setX(x);
        foodPiece.setY(y);
        foodPiece.setFoodCoordinates(new int[]{x, y, foodPiece.getWidth(), foodPiece.getHeight()});
        repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.red);
                g2.fillRect(foodPiece.getFoodCoordinates()[0], foodPiece.getFoodCoordinates()[1], foodPiece.getFoodCoordinates()[2], foodPiece.getFoodCoordinates()[3]);
        } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
    }

}
