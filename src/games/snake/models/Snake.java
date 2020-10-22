package games.snake.models;

import java.util.ArrayList;

public class Snake {

    int length;
    int xFirstElement;
    int yFirstElement;
    int widthFirstElement;
    int heightFirstElement;
    String snakeDirect;
    ArrayList<int[]> elementsPositions;

    public Snake(int xFirstElement, int yFirstElement, int widthFirstElement, int heightFirstElement) {
        this.length = 1;
        this.xFirstElement = xFirstElement;
        this.yFirstElement = yFirstElement;
        this.widthFirstElement = widthFirstElement;
        this.heightFirstElement = heightFirstElement;
        this.snakeDirect = "RIGHT";
        this.elementsPositions = new ArrayList<>();
        elementsPositions.add(createSnakeFragmentPosition(xFirstElement, yFirstElement, widthFirstElement, heightFirstElement));
    }

    public int[] createSnakeFragmentPosition(int x, int y, int width, int height){
        return (new int[]{x, y, width, height});
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

    public int getWidthFirstElement() {
        return widthFirstElement;
    }

    public int getHeightFirstElement() {
        return heightFirstElement;
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

    public void setWidthFirstElement(int widthFirstElement) {
        this.widthFirstElement = widthFirstElement;
    }

    public void setHeightFirstElement(int heightFirstElement) {
        this.heightFirstElement = heightFirstElement;
    }

    public String getSnakeDirect() {
        return snakeDirect;
    }

    public void setSnakeDirect(String snakeDirect) {
        this.snakeDirect = snakeDirect;
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
