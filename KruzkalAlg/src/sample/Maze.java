package sample;

import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Maze {

    private int gridSize;
    Cell[][] cells;
    boolean stopRecursive = false;

    ArrayList<Cell> availableCells = new ArrayList<>();
    ArrayList<Sets> allSets = new ArrayList<>(); // kruskals sets

    public Maze(int gridSize, Pane mainPane) {
        setGridSize(gridSize);
        Cell temp[][] = new Cell[gridSize][gridSize];
        cells = temp;
        int uniqueCounter = 0;
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                Cell cell = new Cell(x, y, 4);
                Sets set = new Sets();
                uniqueCounter++;
                set.cellID = uniqueCounter;
                cell.fieldID = uniqueCounter;
                set.cellSets.add(cell);
                allSets.add(set);
                cell.setX(x);
                cell.setY(y);
                cells[x][y] = cell;


            }
        }
    }

    void draw(Pane mainPane) {
        for (int x = 0; x < getGridSize(); x++) {
            for (int y = 0; y < getGridSize(); y++) {
                cells[x][y].draw(mainPane);
            }
        }
    }

    int maximumVisited = 0;

    void recursive(int x, int y, Pane mainPane) {

        /*
        draw(mainPane);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){

        }*/

        cells[x][y].setVisited(true);
        System.out.println(x + ", " + y);
        maximumVisited++;
        System.out.print(maximumVisited + " steps taken");
        ArrayList<Integer> avaiblePath = new ArrayList<>();
        Random random = new Random();


        //north
        if (y != 0) if (!cells[x][y - 1].isVisited()) {
            avaiblePath.add(0);
        }
        //east
        if (x != gridSize - 1) if (!cells[x + 1][y].isVisited()) {
            avaiblePath.add(1);

        }
        //south
        if (y != gridSize - 1) if (!cells[x][y + 1].isVisited()) {
            avaiblePath.add(2);
        }
        //west
        if (x != 0) if (!cells[x - 1][y].isVisited()) {
            avaiblePath.add(3);
        }

        int chosenPath = 0;
        System.out.println(avaiblePath);
        if (avaiblePath.size() != 0) {
            chosenPath = avaiblePath.get(random.nextInt(avaiblePath.size()));
        } else {
            if (x == 0 && y == 0) {
                System.out.println("this is the end of the line...");
                stopRecursive = true;


            } else {
                System.out.println("I must go back");
                recursive(cells[x][y].getPreviousCell().getX(), cells[x][y].getPreviousCell().getY(), mainPane);
            }
        }

        if (!stopRecursive) {
            if (chosenPath == 0) {
                cells[x][y - 1].setWalls(2, false);
                cells[x][y].setWalls(0, false);
                System.out.println("Went North");
                cells[x][y - 1].setPreviousCell(cells[x][y]);
                recursive(x, y - 1, mainPane);
            }
            if (chosenPath == 1) {
                cells[x + 1][y].setWalls(3, false);
                cells[x][y].setWalls(1, false);
                cells[x + 1][y].setPreviousCell(cells[x][y]);
                System.out.println("Went West");
                recursive(x + 1, y, mainPane);
            }
            if (chosenPath == 2) {
                cells[x][y + 1].setWalls(0, false);
                cells[x][y].setWalls(2, false);
                cells[x][y + 1].setPreviousCell(cells[x][y]);
                System.out.println("Went South");
                recursive(x, y + 1, mainPane);
            }
            if (chosenPath == 3) {
                cells[x - 1][y].setWalls(1, false);
                cells[x][y].setWalls(3, false);
                cells[x - 1][y].setPreviousCell(cells[x][y]);
                System.out.println("Went East");
                recursive(x - 1, y, mainPane);

            }
        }


    }



    Random random = new Random();


    void kruskal() {

        for (int x = 0; x < allSets.size(); x = random.nextInt(allSets.size())) {
            //System.out.println(allSets.size());
            //System.out.println(maximumVisited);
            //maximumVisited++;
            if (allSets.size()  == 1)return;
            Cell chosenCell = allSets.get(x).cellSets.get(random.nextInt(allSets.get(x).cellSets.size()));
            ArrayList<Integer> avaiblePath = new ArrayList<>();
            //north
            //System.out.println(chosenCell.getX() + ", " + chosenCell.getY());
            if (chosenCell.getY() != 0) {
                Cell comparedCell = cells[chosenCell.getX()][chosenCell.getY() - 1];
                if (chosenCell.fieldID != comparedCell.fieldID) {
                    avaiblePath.add(0);
                }
            }
            //east
            if (chosenCell.getX() != gridSize - 1) {
                Cell comparedCell = cells[chosenCell.getX() + 1][chosenCell.getY()];
                if (chosenCell.fieldID != comparedCell.fieldID) {
                    avaiblePath.add(1);
                }
            }
            //south
            if (chosenCell.getY() != gridSize - 1) {
                Cell comparedCell = cells[chosenCell.getX()][chosenCell.getY() + 1];
                if (chosenCell.fieldID != comparedCell.fieldID) {
                    avaiblePath.add(2);
                }
            }
            //west
            if (chosenCell.getX() != 0) {
                Cell comparedCell = cells[chosenCell.getX() - 1][chosenCell.getY()];
                if (chosenCell.fieldID != comparedCell.fieldID) {
                    avaiblePath.add(3);
                }
            }
            int chosenPath = 5;
            //System.out.println(avaiblePath);
            if (avaiblePath.size() != 0) {
                chosenPath = avaiblePath.get(random.nextInt(avaiblePath.size()));
            } else {

                chosenCell.setVisited(true);

            }

            if (chosenPath == 0) {
                chosenCell.setWalls(0, false);
                cells[chosenCell.getX()][chosenCell.getY() - 1].setWalls(2, false);
                for (int q = 0; q < allSets.size(); q++) {
                    if (allSets.get(q).cellID == cells[chosenCell.getX()][chosenCell.getY() - 1].fieldID) {
                        for (int p = 0; p < allSets.size(); p++) {
                            if (chosenCell.fieldID == allSets.get(p).cellID) {
                                for (int f = 0; f < allSets.get(q).cellSets.size(); f++) {
                                    allSets.get(q).cellSets.get(f).fieldID = chosenCell.fieldID;
                                }
                                allSets.get(p).cellSets.addAll(allSets.get(q).cellSets);
                                p = allSets.size();
                            }
                        }
                        allSets.remove(q);
                        q = allSets.size();
                    }
                }
                //cells[chosenCell.getX()][chosenCell.getY()-1].fieldID = chosenCell.fieldID;
            }

            if (chosenPath == 1) {
                chosenCell.setWalls(1, false);
                cells[chosenCell.getX()+1][chosenCell.getY()].setWalls(3, false);
                cells[chosenCell.getX()][chosenCell.getY()] = chosenCell;
                for (int q = 0; q < allSets.size(); q++) {
                    if (allSets.get(q).cellID == cells[chosenCell.getX() + 1][chosenCell.getY()].fieldID) {
                        for (int p = 0; p < allSets.size(); p++) {
                            if (chosenCell.fieldID == allSets.get(p).cellID) {
                                for (int f = 0; f < allSets.get(q).cellSets.size(); f++) {
                                    allSets.get(q).cellSets.get(f).fieldID = chosenCell.fieldID;
                                }
                                allSets.get(p).cellSets.addAll(allSets.get(q).cellSets);
                                p = allSets.size();
                            }
                        }
                        allSets.remove(q);
                        q = allSets.size();
                    }
                }
                //cells[chosenCell.getX()][chosenCell.getY()-1].fieldID = chosenCell.fieldID;
            }
            if (chosenPath == 2) {
                chosenCell.setWalls(2, false);
                cells[chosenCell.getX()][chosenCell.getY()+1].setWalls(0, false);
                cells[chosenCell.getX()][chosenCell.getY()] = chosenCell;

                for (int q = 0; q < allSets.size(); q++) {
                    if (allSets.get(q).cellID == cells[chosenCell.getX()][chosenCell.getY() + 1].fieldID) {
                        for (int p = 0; p < allSets.size(); p++) {
                            if (chosenCell.fieldID == allSets.get(p).cellID) {
                                for (int f = 0; f < allSets.get(q).cellSets.size(); f++) {
                                    allSets.get(q).cellSets.get(f).fieldID = chosenCell.fieldID;
                                }
                                allSets.get(p).cellSets.addAll(allSets.get(q).cellSets);
                                p = allSets.size();
                            }
                        }
                        allSets.remove(q);
                        q = allSets.size();
                    }
                }
                //cells[chosenCell.getX()][chosenCell.getY()-1].fieldID = chosenCell.fieldID;
            }
            if (chosenPath == 3) {
                chosenCell.setWalls(3, false);
                cells[chosenCell.getX()-1][chosenCell.getY()].setWalls(1, false);
                cells[chosenCell.getX()][chosenCell.getY()] = chosenCell;
                for (int q = 0; q < allSets.size(); q++) {
                    if (allSets.get(q).cellID == cells[chosenCell.getX() - 1][chosenCell.getY()].fieldID) {
                        for (int p = 0; p < allSets.size(); p++) {
                            if (chosenCell.fieldID == allSets.get(p).cellID) {
                                for (int f = 0; f < allSets.get(q).cellSets.size(); f++) {
                                    allSets.get(q).cellSets.get(f).fieldID = chosenCell.fieldID;
                                }
                                allSets.get(p).cellSets.addAll(allSets.get(q).cellSets);
                                p = allSets.size();
                            }
                        }
                        allSets.remove(q);
                        q = allSets.size();
                    }
                }
                //cells[chosenCell.getX()][chosenCell.getY()-1].fieldID = chosenCell.fieldID;
            }

        }
    }



    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }


}
