package games.snake.controllers;

import games.snake.models.FoodPiece;
import games.snake.models.Snake;
import games.snake.views.GameFinishedView;
import games.snake.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;

//TODO: FIX MVC
//TODO: make func randomly spawning food
//TODO: make snake powers
//TODO: make snake eats food and grows
//TODO: makes snake dies when touched side or itself

public class ControllerSnake {

    private int turn = 1;
    Snake snake;
    DrawSnakePiece drawSnakePiece;
    MainView mainView;
    DrawFoodPiece drawFoodPiece;
    int gameDifficulty;

    public ControllerSnake(int gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.snake = new Snake(20,60,20,20);
        this.drawSnakePiece = new DrawSnakePiece();
        this.drawFoodPiece = new DrawFoodPiece();
        this.mainView = new MainView(drawSnakePiece, drawFoodPiece);
        drawSnakePiece.moveSnake(snake.getElementsPositions());

//      SNAKE AUTO MOVE
        Thread snakeAutoMove = new Thread() {
            public void run() {
                try {
                while(true) {
                    switch (snake.getSnakeDirect()) {
                        case "RIGHT":
                            makeMove((snake.getxFirstElement() + 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                            break;
                        case "LEFT":
                            makeMove((snake.getxFirstElement() - 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                            break;
                        case "UP":
                            makeMove(snake.getxFirstElement(), (snake.getyFirstElement() - 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                            break;
                        case "DOWN":
                            makeMove(snake.getxFirstElement(), (snake.getyFirstElement() + 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                            break;
                    }

                    if (testIfSnakesLives() == false){
                        System.out.println("GAME LOST");
                        mainView.getFrame().dispose();
                        new GameFinishedView();
                        break;
                    }

                    Thread.sleep(gameDifficulty);

                }
                } catch(InterruptedException v) {
                    System.out.println(v);
                }
            }
        };
        snakeAutoMove.start();

//        RIGHT MOVE
        Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if (snake.getxFirstElement() + 10 < mainView.getGameFieldPanel().getWidth()-snake.getWidthFirstElement()){
                    makeMove((snake.getxFirstElement() + 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("RIGHT");
                }
            }
        };

//        LEFT MOVE
        Action leftAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if (snake.getxFirstElement() - 10 > 0) {
                    makeMove((snake.getxFirstElement() - 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("LEFT");
                }
            }
        };

//        TOP MOVE
        Action topAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if (snake.getyFirstElement() - 10 > 0) {
                    makeMove(snake.getxFirstElement(), (snake.getyFirstElement() - 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("UP");
                }
            }
        };

//        BOTTOM MOVE
        Action bottomAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if (snake.getyFirstElement() + 10 < mainView.getGameFieldPanel().getHeight()-snake.getHeightFirstElement()) {
                    makeMove(snake.getxFirstElement(), (snake.getyFirstElement() + 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("DOWN");
                }
            }
        };


        InputMap inputMap = mainView.getGameFieldPanel().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap =  mainView.getGameFieldPanel().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        actionMap.put("rightAction", rightAction);

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        actionMap.put("leftAction", leftAction);

        inputMap.put(KeyStroke.getKeyStroke("UP"), "topAction");
        actionMap.put("topAction", topAction);

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "bottomAction");
        actionMap.put("bottomAction", bottomAction);

    }


public boolean testIfSnakesLives(){
//        Hit in right wall
        if (snake.getElementsPositions().get(0)[0] >= mainView.getGameFieldPanel().getWidth()-snake.getWidthFirstElement()){
            return false;
        }
//            Hit in left wall
    if (snake.getElementsPositions().get(0)[0] <= 0){
        return false;
    }
//            Hit in top wall
    if (snake.getElementsPositions().get(0)[1] >= mainView.getGameFieldPanel().getHeight()-snake.getHeightFirstElement()){
        return false;
    }
//            Hit in bottom wall
    if (snake.getElementsPositions().get(0)[1] <= 0){
        return false;
    }
    return true;
    }

    public void makeMove(int x, int y, int width, int height){
        snake.setxFirstElement(x);
        snake.setyFirstElement(y);
        snake.setWidthFirstElement(width);
        snake.setHeightFirstElement(height);
        for (int i=snake.getElementsPositions().size()-1; i>1; i--){
            snake.getElementsPositions().set(i, snake.getElementsPositions().get(i-1));
        }
        snake.getElementsPositions().set(0, snake.createSnakeFragmentPosition(x, y, width, height));
        drawSnakePiece.moveSnake(snake.getElementsPositions());
    }

    public void addSnakeFrgment(int x, int y, int width, int height){
        snake.getElementsPositions().add(snake.createSnakeFragmentPosition(x, y, width, height));
    }

    public Snake getSnake() {
        return snake;
    }
}
