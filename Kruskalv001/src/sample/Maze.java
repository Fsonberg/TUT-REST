package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int size;
    Cell[][] walls;
    boolean stopRecursive = false;


    ArrayList<Sets> setList = new ArrayList<>(); // kruskals sets

    public Maze(int size, Pane mainPane) {
        setSize(size);
        Cell temp[][] = new Cell[size][size];
        walls = temp;
        int cellCount = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = new Cell(x, y, 20);
                Sets set = new Sets();
                cellCount++;
                set.cellID = cellCount;
                cell.fieldID = cellCount;
                set.cellSets.add(cell);
                setList.add(set);
                cell.setX(x);
                cell.setY(y);
                walls[x][y] = cell;


            }
        }
    }

    void sketch(Pane mainGrid) {
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                walls[x][y].sketch(mainGrid);
            }
        }
    }

    int calcCount = 0;

    Random random = new Random();


    void kruskalAlg() {

        for (int x = 0; x < setList.size(); x = random.nextInt(setList.size())) {
            System.out.println(setList.size());
            System.out.println(calcCount);
            calcCount++;
            if (setList.size()  == 1)return;
            Cell specificCell = setList.get(x).cellSets.get(random.nextInt(setList.get(x).cellSets.size()));
            ArrayList<Integer> path = new ArrayList<>();
            //north

            if (specificCell.getY() != 0) {
                Cell cellToCompare = walls[specificCell.getX()][specificCell.getY() - 1];
                if (specificCell.fieldID != cellToCompare.fieldID) {
                    path.add(0);
                }
            }
            //east
            if (specificCell.getX() != size - 1) {
                Cell cellToCompare = walls[specificCell.getX() + 1][specificCell.getY()];
                if (specificCell.fieldID != cellToCompare.fieldID) {
                    path.add(1);
                }
            }
            //south
            if (specificCell.getY() != size - 1) {
                Cell cellToCompare = walls[specificCell.getX()][specificCell.getY() + 1];
                if (specificCell.fieldID != cellToCompare.fieldID) {
                    path.add(2);
                }
            }
            //west
            if (specificCell.getX() != 0) {
                Cell cellToCompare = walls[specificCell.getX() - 1][specificCell.getY()];
                if (specificCell.fieldID != cellToCompare.fieldID) {
                    path.add(3);
                }
            }
            int specificPath = 5;

            if (path.size() != 0) {
                specificPath = path.get(random.nextInt(path.size()));
            } else {

                specificCell.setVisited(true);

            }

            if (specificPath == 0) {
                specificCell.setWalls(0, false);
                walls[specificCell.getX()][specificCell.getY() - 1].setWalls(2, false);
                for (int a = 0; a < setList.size(); a++) {
                    if (setList.get(a).cellID == walls[specificCell.getX()][specificCell.getY() - 1].fieldID) {
                        for (int b = 0; b < setList.size(); b++) {
                            if (specificCell.fieldID == setList.get(b).cellID) {
                                for (int c = 0; c < setList.get(a).cellSets.size(); c++) {
                                    setList.get(a).cellSets.get(c).fieldID = specificCell.fieldID;
                                }
                                setList.get(b).cellSets.addAll(setList.get(a).cellSets);
                                b = setList.size();
                            }
                        }
                        setList.remove(a);
                        a = setList.size();
                    }
                }

            }

            if (specificPath == 1) {
                specificCell.setWalls(1, false);
                walls[specificCell.getX()+1][specificCell.getY()].setWalls(3, false);
                walls[specificCell.getX()][specificCell.getY()] = specificCell;
                for (int a = 0; a < setList.size(); a++) {
                    if (setList.get(a).cellID == walls[specificCell.getX() + 1][specificCell.getY()].fieldID) {
                        for (int b = 0; b < setList.size(); b++) {
                            if (specificCell.fieldID == setList.get(b).cellID) {
                                for (int c = 0; c < setList.get(a).cellSets.size(); c++) {
                                    setList.get(a).cellSets.get(c).fieldID = specificCell.fieldID;
                                }
                                setList.get(b).cellSets.addAll(setList.get(a).cellSets);
                                b = setList.size();
                            }
                        }
                        setList.remove(a);
                        a = setList.size();
                    }
                }

            }
            if (specificPath == 2) {
                specificCell.setWalls(2, false);
                walls[specificCell.getX()][specificCell.getY()+1].setWalls(0, false);
                walls[specificCell.getX()][specificCell.getY()] = specificCell;

                for (int a = 0; a < setList.size(); a++) {
                    if (setList.get(a).cellID == walls[specificCell.getX()][specificCell.getY() + 1].fieldID) {
                        for (int b = 0; b < setList.size(); b++) {
                            if (specificCell.fieldID == setList.get(b).cellID) {
                                for (int c = 0; c < setList.get(a).cellSets.size(); c++) {
                                    setList.get(a).cellSets.get(c).fieldID = specificCell.fieldID;
                                }
                                setList.get(b).cellSets.addAll(setList.get(a).cellSets);
                                b = setList.size();
                            }
                        }
                        setList.remove(a);
                        a = setList.size();
                    }
                }

            }
            if (specificPath == 3) {
                specificCell.setWalls(3, false);
                walls[specificCell.getX()-1][specificCell.getY()].setWalls(1, false);
                walls[specificCell.getX()][specificCell.getY()] = specificCell;
                for (int a = 0; a < setList.size(); a++) {
                    if (setList.get(a).cellID == walls[specificCell.getX() - 1][specificCell.getY()].fieldID) {
                        for (int b = 0; b < setList.size(); b++) {
                            if (specificCell.fieldID == setList.get(b).cellID) {
                                for (int c = 0; c < setList.get(a).cellSets.size(); c++) {
                                    setList.get(a).cellSets.get(c).fieldID = specificCell.fieldID;
                                }
                                setList.get(b).cellSets.addAll(setList.get(a).cellSets);
                                b = setList.size();
                            }
                        }
                        setList.remove(a);
                        a = setList.size();
                    }
                }

            }

        }
    }






    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
