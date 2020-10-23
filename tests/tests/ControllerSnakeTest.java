package tests;

import games.snake.controllers.ControllerSnake;
import games.snake.models.Snake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerSnakeTest {

    static ControllerSnake controllerSnake;

    @BeforeEach
    void setup(){
        controllerSnake = new ControllerSnake(60, 7000);
    }
//TODO:  add some snake elements and test
    @Test
    public void makeMoveXTest(){
        controllerSnake.makeMove(3, 2, 2, 2);
        assertEquals(3, controllerSnake.getSnake().getxFirstElement());
    }

    @Test
    public void makeMoveYTest(){
        controllerSnake.makeMove(2, 2, 2,2);
        assertEquals(2, controllerSnake.getSnake().getyFirstElement());
    }

//    @Test
//    public void addSnakeXFragmentTest(){
//        controllerSnake.addSnakeFrgment(9,2,2, 2);
//        assertEquals( 9 ,controllerSnake.getSnake().getElementsPositions().get(controllerSnake.getSnake().getElementsPositions().size()-1)[0]);
//    }
//
//    @Test
//    public void addSnakeYFragmentTest(){
//        controllerSnake.addSnakeFrgment(2,18, 2,2);
//        assertEquals( 18 ,controllerSnake.getSnake().getElementsPositions().get(controllerSnake.getSnake().getElementsPositions().size()-1)[1]);
//    }

}