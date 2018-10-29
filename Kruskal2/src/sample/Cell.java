package sample;

import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;


import java.awt.*;


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

    void draw(Pane mainPane){
        //north
        //Text t = new Text (getX() * getCellSize(), getY() * getCellSize(), String.valueOf(fieldID));
        //mainPane.getChildren().add(t);
        if(getWalls()[0]){
            Line line = new Line(getX() * getCellSize(), getY() * getCellSize(),
                    getX() * getCellSize() + getCellSize(), getY() * getCellSize());
            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
        }
        // south
        if(getWalls()[2]){
            Line line = new Line(getX() * getCellSize(), getY() * getCellSize() + getCellSize(),
                    getX() * getCellSize() + getCellSize(), getY() * getCellSize() + getCellSize());
            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
        }
        //east
        if(getWalls()[1]){
            Line line = new Line(getX() * getCellSize() + getCellSize(), getY() * getCellSize(), getX() * getCellSize() + getCellSize(), getY() * getCellSize() + getCellSize());
            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
        }
        //west
        if(getWalls()[3]){
            Line line = new Line(getX() * getCellSize(), getY() * getCellSize(), getX() * getCellSize(), getY() * getCellSize() + getCellSize());
            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
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

