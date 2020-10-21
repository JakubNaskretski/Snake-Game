package games.snake.models;

import java.util.ArrayList;

public class Snake {

    int length;
    int xFirstElement;
    int yFirstElement;
    ArrayList<int[]> elementsPositions;

    public Snake() {
        this.length = 1;
        this.xFirstElement = 0;
        this.yFirstElement = 0;
        this.elementsPositions = new ArrayList<>();
        elementsPositions.add(createSnakeFragmentPosition(xFirstElement, yFirstElement));
    }

    public int[] createSnakeFragmentPosition(int x, int y){
        return (new int[]{x, y});
    }

    public int getLength() {
        return length;
    }

    public int getxFirstElement() {
        return xFirstElement;
    }

    public int getyFirstElement() {
        return yFirstElement;
    }

    public ArrayList<int[]> getElementsPositions() {
        return elementsPositions;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setxFirstElement(int xFirstElement) {
        this.xFirstElement = xFirstElement;
    }

    public void setyFirstElement(int yFirstElement) {
        this.yFirstElement = yFirstElement;
    }

    public void setElementsPositions(ArrayList<int[]> elementsPositions) {
        this.elementsPositions = elementsPositions;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "length=" + length +
                ", xFirstElement=" + xFirstElement +
                ", yFirstElement=" + yFirstElement +
                ", elementsPositions=" + elementsPositions +
                '}';
    }
}
