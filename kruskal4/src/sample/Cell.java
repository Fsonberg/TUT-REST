package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Cell {


    private int x;
    private int y;
    private int cellSides = 4;

    private boolean[] sides = new boolean[getCellSides()];
    private boolean visitCheck;
    private int cellSize;
    private Cell sidsteCell;
    int fieldID;


    public Cell(int x, int y, int cellSize){

            this.setX(x);
            this.setY(y);
            this.setCellSides(cellSize);

        for (int i = 0; i < getCellSides(); i++) { //generer siderne på cellerne
            getSides()[i] = true; //set siderne i for-loopet til true

        }
        setVisitCheck(false); // så ingen er besøgte.

    }

    void sketch (Pane mainPane){
        if(getSides()[0]){
            Line wall = new Line(getX()*getCellSize(), getY()*getCellSize(),getX()*getCellSize()+getCellSize(),getY()*getCellSize());
            wall.setSmooth(false);
            wall.setStrokeWidth(1);
            mainPane.getChildren().add(wall);
        }
        if(getSides()[2]){
            Line line = new Line(getX() * getCellSize(), getY() * getCellSize() + getCellSize(),
                    getX() * getCellSize() + getCellSize(), getY() * getCellSize() + getCellSize());

            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
        }
        if(getSides()[1]){
            Line line = new Line(getX() * getCellSize() + getCellSize(),
                    getY() * getCellSize(), getX() * getCellSize() + getCellSize(), getY() * getCellSize() + getCellSize());
            line.setSmooth(false);
            line.setStrokeWidth(1);
            mainPane.getChildren().add(line);
        }
        if(getSides()[3]){
            Line line = new Line(getX() * getCellSize(), getY() * getCellSize(),
                    getX() * getCellSize(), getY() * getCellSize() + getCellSize());
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

    public int getCellSides() {
        return cellSides;
    }

    public void setCellSides(int cellSides) {
        this.cellSides = cellSides;
    }

    public boolean[] getSides() {
        return sides;
    }

    public void setSides(boolean[] sides) {
        this.sides = sides;
    }

    public boolean isVisitCheck() {
        return visitCheck;
    }

    public void setVisitCheck(boolean visitCheck) {
        this.visitCheck = visitCheck;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public Cell getSidsteCell() {
        return sidsteCell;
    }

    public void setSidsteCell(Cell sidsteCell) {
        this.sidsteCell = sidsteCell;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
}
