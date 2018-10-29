package sample;

import javafx.scene.layout.*;
import javafx.scene.shape.*;



public class Cell {
    private int x;
    private int y;
    private int wallAmount = 4;
    private boolean[] walls = new boolean[getWallAmount()];
    private boolean visited;
    private int cellSize;
    private Cell previousCell;
    int fieldID;



    public Cell(int x, int y, int cellSize) {
        this.setCellSize(cellSize);
        this.setX(x);
        this.setY(y);
        for (int q = 0; q < getWallAmount(); q++){
            getWalls()[q] = true;
        }
        setVisited(false);
    }

    void sketch(Pane mainPane){
        //north
        int getXsize = getX() * getCellSize();
        int getYsize = getY() * getCellSize();

        if(getWalls()[0]){
            Line upperLine = new Line(getXsize, getYsize,
                    getXsize + getCellSize(), getYsize);

            upperLine.setSmooth(false);
            upperLine.setStrokeWidth(1);
            mainPane.getChildren().add(upperLine);
        }
        // south
        if(getWalls()[2]){
            Line lowerLine = new Line(getXsize, getYsize + getCellSize(),
                    getXsize + getCellSize(), getYsize + getCellSize());

            lowerLine.setSmooth(false);
            lowerLine.setStrokeWidth(1);
            mainPane.getChildren().add(lowerLine);
        }
        //east
        if(getWalls()[1]){
            Line rightLine = new Line(getXsize + getCellSize(), getYsize,
                     getXsize + getCellSize(), getYsize + getCellSize());
            rightLine.setSmooth(false);
            rightLine.setStrokeWidth(1);
            mainPane.getChildren().add(rightLine);
        }
        //west
        if(getWalls()[3]){
            Line leftLine = new Line(getXsize, getYsize,
                    getXsize, getYsize + getCellSize());
            leftLine.setSmooth(false);
            leftLine.setStrokeWidth(1);
            mainPane.getChildren().add(leftLine);
        }



    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWallAmount() {
        return wallAmount;
    }

    public void setWallAmount(int wallAmount) {
        this.wallAmount = wallAmount;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(int pos, boolean wall) {
        this.walls[pos] = wall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public Cell getPreviousCell() {
        return previousCell;
    }

    public void setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
    }
}

