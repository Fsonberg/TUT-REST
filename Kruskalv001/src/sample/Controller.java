package sample;

import javafx.scene.layout.Pane;

public class Controller {


    public Pane mainPane;

    public void initialize(){
        Maze myMaze = new Maze(50, mainPane);
        myMaze.kruskalAlg();
        //myMaze.recursive(0,0,mainPane);
        myMaze.sketch(mainPane);
    }
}
