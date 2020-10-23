package games.snake.controllers;

import games.snake.models.SnakePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

//TODO:  Split for MVC

public class DrawSnakePiece extends JComponent {

    private ArrayList<int[]> snakeElements;
    private ArrayList<Rectangle> snakeRectangles;

    public DrawSnakePiece() {
        snakeRectangles = new ArrayList<>();
    }

    //    Gets snake pieces coordinates, copies it and draws new snake on a board
    public void moveSnake(ArrayList<int[]> snakeElements){
        this.snakeElements = new ArrayList<>(snakeElements);
//        this.snakeRectangles.clear();
        repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        try {
            this.snakeRectangles.clear();
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int[] snakeElement : snakeElements) {
                g2.setColor(Color.BLACK);
                Rectangle snakeRectangleElement= new Rectangle(snakeElement[0], snakeElement[1], snakeElement[2], snakeElement[3]);
                this.snakeRectangles.add(snakeRectangleElement);
                g2.fill(snakeRectangleElement);
//                g2.drawRect( snakeElement[0], snakeElement[1], snakeElement[2], snakeElement[3]);
            }
        } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
    }

    public ArrayList<Rectangle> getSnakeRectangles() {
        return snakeRectangles;
    }
}
