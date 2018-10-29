package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.WHITE;

public class Controller {

    public Pane mainPane;

    public void initialize(){

        Maze myMaze = new Maze(10,mainPane);
        myMaze.kruskal();
        myMaze.draw(mainPane);
    }

}
