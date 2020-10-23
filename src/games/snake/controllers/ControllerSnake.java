package games.snake.controllers;

import games.snake.models.Snake;
import games.snake.views.GameFinishedView;
import games.snake.views.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.TimerTask;

//TODO: FIX MVC
//TODO: WRITE TESTS

public class ControllerSnake {

    Snake snake;
    DrawSnakePiece drawSnakePiece;
    DrawFoodPiece drawFoodPiece;
    MainView mainView;
    int gameDifficulty;
    int foodChangeCounter;
    int foodPoints, playerPoints;
    Timer timerObj;

    public ControllerSnake(int gameDifficulty, int foodChangeCounter) {
        this.gameDifficulty = gameDifficulty;
        this.playerPoints = 0;
        this.foodChangeCounter = foodChangeCounter;
        this.foodPoints = 5;
        this.foodChangeCounter = 10;
        this.snake = new Snake(20,60,20,20);
        this.drawSnakePiece = new DrawSnakePiece();
        this.drawFoodPiece = new DrawFoodPiece();
        this.mainView = new MainView(drawSnakePiece, drawFoodPiece);
        drawSnakePiece.moveSnake(snake.getElementsPositions());


        this.timerObj = new Timer(foodChangeCounter, (e -> {
            foodPoints--;
            if (foodPoints <= 0) {
                changeFoodPlace();
            }
        }));

        snakeAutoMove.start();
        timerObj.start();

//        RIGHT MOVE
        Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
//                if (snake.getxFirstElement() + 10 < mainView.getGameFieldPanel().getWidth()-snake.getWidthFirstElement()){
                    makeMove((snake.getxFirstElement() + 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("RIGHT");
//                }
            }
        };

//        LEFT MOVE
        Action leftAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
//                if (snake.getxFirstElement() - 10 > 0) {
                    makeMove((snake.getxFirstElement() - 10), snake.getyFirstElement(), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("LEFT");
//                }
            }
        };

//        TOP MOVE
        Action topAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
//                if (snake.getyFirstElement() - 10 > 0) {
                    makeMove(snake.getxFirstElement(), (snake.getyFirstElement() - 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("UP");
//                }
            }
        };

//        BOTTOM MOVE
        Action bottomAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
//                if (snake.getyFirstElement() + 10 < mainView.getGameFieldPanel().getHeight()-snake.getHeightFirstElement()) {
                    makeMove(snake.getxFirstElement(), (snake.getyFirstElement() + 10), snake.getWidthFirstElement(), snake.getHeightFirstElement());
                    snake.setSnakeDirect("DOWN");
//                }
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


    public boolean ifSnakesHitWall(){
//            Hit in right wall
        if (snake.getElementsPositions().get(0)[0] >= mainView.getGameFieldPanel().getWidth()){
            return true;
        }
//            Hit in left wall
    if (snake.getElementsPositions().get(0)[0] <= -10){
        return true;
    }
//            Hit in top wall
    if (snake.getElementsPositions().get(0)[1] >= mainView.getGameFieldPanel().getHeight()){
        return true;
    }
//            Hit in bottom wall
    if (snake.getElementsPositions().get(0)[1] <= -10){
        return true;
    }
    return false;
    }

    public boolean ifSnakeAteItself(){
        for (int i = 4; i<drawSnakePiece.getSnakeRectangles().size()-1; i++){
            if (drawSnakePiece.getSnakeRectangles().get(i).getBounds().intersects(drawSnakePiece.getSnakeRectangles().get(0).getBounds())){
                double areaOfIntersectedRectangle = (drawSnakePiece.getSnakeRectangles().get(i).getBounds().intersection(drawSnakePiece.getSnakeRectangles().get(0).getBounds()).getHeight()*
                        (drawSnakePiece.getSnakeRectangles().get(i).getBounds().intersection(drawSnakePiece.getSnakeRectangles().get(0).getBounds()).getHeight()));
                if (((areaOfIntersectedRectangle) > 25.0)){
                    return true;
                }
            }
        }
        return false;
        }

//    public boolean ifAteFood() {
////        Left top corner of the snake
//        if ((snake.getxFirstElement() >= drawFoodPiece.getFoodPiece().getX()) && (snake.getxFirstElement() <= drawFoodPiece.getFoodPiece().getX() + drawFoodPiece.getFoodPiece().getWidth())) {
//            if (snake.getyFirstElement() >= drawFoodPiece.getFoodPiece().getY() && snake.getyFirstElement() <= drawFoodPiece.getFoodPiece().getY() + drawFoodPiece.getFoodPiece().getHeight()) {
//                System.out.println("Zjedzone lewa góra !");
//                return true;
//            } else if (snake.getyFirstElement()+snake.getHeightFirstElement() >= drawFoodPiece.getFoodPiece().getY() && snake.getyFirstElement()+snake.getHeightFirstElement() <= drawFoodPiece.getFoodPiece().getY() + drawFoodPiece.getFoodPiece().getHeight()){
//                System.out.println("Zjedzone lewy dół !");
//                return true;
//            }
//        }
//        //        Right top corner of the snake and Right bottom corner
//        if ((snake.getxFirstElement() + snake.getWidthFirstElement() >= drawFoodPiece.getFoodPiece().getX()) && (snake.getxFirstElement() + snake.getWidthFirstElement() <= drawFoodPiece.getFoodPiece().getX() + drawFoodPiece.getFoodPiece().getWidth())) {
//            if (snake.getyFirstElement() <= drawFoodPiece.getFoodPiece().getY() + drawFoodPiece.getFoodPiece().getHeight() && snake.getyFirstElement() >= drawFoodPiece.getFoodPiece().getY()) {
//                System.out.println("Zjedzony prawa góra !");
//                return true;
//            } else if (snake.getyFirstElement() + snake.getHeightFirstElement() >= drawFoodPiece.getFoodPiece().getY() && snake.getyFirstElement() + snake.getHeightFirstElement() <= drawFoodPiece.getFoodPiece().getY() + drawFoodPiece.getFoodPiece().getHeight()) {
//                System.out.println("Zjedzony prwy dół !");
//
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean ifAteFood() {
//        Left top corner of the snake
        if (drawSnakePiece.getSnakeRectangles().size() > 0){
        if ((drawSnakePiece.getSnakeRectangles().get(0).getBounds().intersects(drawFoodPiece.getFoodPiece().getFoodPiece().getBounds()))) {
                return true;
        }}
        return false;
    }

    public void makeMove(int x, int y, int width, int height){
        snake.setxFirstElement(x);
        snake.setyFirstElement(y);
        snake.setWidthFirstElement(width);
        snake.setHeightFirstElement(height);
        for (int i=snake.getElementsPositions().size()-1; i>0; i--){
            snake.getElementsPositions().set(i, snake.getElementsPositions().get(i-1));
        }
//        snake.getElementsPositions().set(0, snake.createSnakeFragmentPosition(new int[]{x, y, width, height}));
        snake.getElementsPositions().set(0, snake.createSnakeFragmentPosition(x, y, width, height));
        drawSnakePiece.moveSnake(snake.getElementsPositions());

        if (snake.getHeightFirstElement() > 20){
            snake.setxFirstElement(snake.getxFirstElement()+5);
            snake.setyFirstElement(snake.getyFirstElement()+5);
            snake.setWidthFirstElement(20);
            snake.setHeightFirstElement(20);
        }

    }

//    public void addSnakeFrgment(int x, int y, int width, int height){
//        snake.getElementsPositions().add(snake.createSnakeFragmentPosition(x, y, width, height));
//        snake.setLength(snake.getLength()+1);
//    }

    public void addSnakeFragment(){
//        snake.getElementsPositions().add(snake.createSnakeFragmentPosition(snake.getElementsPositions().get(snake.getLength()-1)));
        snake.getElementsPositions().add(snake.createSnakeFragmentPosition(snake.getElementsPositions().get(snake.getLength()-1)[0],
                snake.getElementsPositions().get(snake.getLength()-1)[1],
                20,
                20));
        snake.setLength(snake.getLength()+1);
    }

    public Snake getSnake() {
        return snake;
    }


    public void speedUpTheGame(){
        if (gameDifficulty > 10){
            if ((snake.getLength()%2) == 0){
                this.gameDifficulty=gameDifficulty-10;
                System.out.println(gameDifficulty);
            }
        }
    }


    public void changeFoodPlace() {

            drawFoodPiece.removeFoodPiece();
            generateNewFoodPieceInRandomPlace();
            this.foodPoints = 10;
            }


    public void generateNewFoodPieceInRandomPlace(){
        int maxX = Math.round(mainView.getGameFieldPanel().getWidth()-20);
        int maxY = Math.round(mainView.getGameFieldPanel().getHeight()-20);
        int min = 10;
        int rangeX = maxX - min + 1;
        int rangeY = maxY - min + 1;
        int randX = (int)(Math.random() * rangeX) + min;
        int randY = (int)(Math.random() * rangeY) + min;

        drawFoodPiece.createNewFoodPiece(randX, randY, Color.RED);
    }

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

                    if (ifAteFood() == true){
                        drawFoodPiece.removeFoodPiece();
                        addSnakeFragment();
                        playerPoints = playerPoints+foodPoints;
                        mainView.getScoreCounter().setText(String.valueOf(playerPoints));
                        mainView.getMainPanel().repaint();
                        foodPoints = 10;

                        System.out.println("Punkty gracza "+playerPoints);
                        System.out.println("Punkty jedzenia "+foodPoints);

//                      Sate first elem big
                        snake.setxFirstElement(snake.getxFirstElement()-5);
                        snake.setyFirstElement(snake.getyFirstElement()-5);
                        snake.setWidthFirstElement(30);
                        snake.setHeightFirstElement(30);

//                        Generates random food place
                        generateNewFoodPieceInRandomPlace();
                        speedUpTheGame();

                    }
                    if (ifSnakesHitWall() == true || ifSnakeAteItself() == true){


                        System.out.println("GAME LOST");
                        mainView.getFrame().dispose();
                        new GameFinishedView(playerPoints);
                        break;
                    }

                    Thread.sleep(gameDifficulty);

                }
            } catch(InterruptedException v) {
                System.out.println(v);
            }
        }
    };

}
