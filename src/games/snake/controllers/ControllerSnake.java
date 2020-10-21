package games.snake.controllers;

import games.snake.models.Snake;

public class ControllerSnake {

    Snake snake;

    public ControllerSnake() {
        this.snake = new Snake();
    }

    public void makeMove(int x, int y){
        snake.setxFirstElement(x);
        snake.setyFirstElement(y);
        for (int i=snake.getElementsPositions().size()-1; i>1; i--){
            snake.getElementsPositions().set(i, snake.getElementsPositions().get(i-1));
        }
        snake.getElementsPositions().set(0, snake.createSnakeFragmentPosition(x, y));
    }

    public void addSnakeFrgment(int x, int y){
        snake.getElementsPositions().add (snake.createSnakeFragmentPosition(x, y));
    }

    public Snake getSnake() {
        return snake;
    }
}
