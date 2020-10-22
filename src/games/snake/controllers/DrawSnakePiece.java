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


//    Gets snake pieces coordinates, copies it and draws new snake on a board
    public void moveSnake(ArrayList<int[]> snakeElements){
        this.snakeElements = new ArrayList<>(snakeElements);
        repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int[] snakeElement : snakeElements) {
                g2.setColor(Color.BLACK);
                g2.fillRect(snakeElement[0], snakeElement[1], snakeElement[2], snakeElement[3]);
//                g2.drawRect( snakeElement[0], snakeElement[1], snakeElement[2], snakeElement[3]);
            }
        } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
    }

}
