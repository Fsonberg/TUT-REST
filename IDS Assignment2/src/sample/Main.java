package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

    public abstract class Main extends Application {


    public void ButtonMashing(Stage stage) {
        BorderPane pane = new BorderPane();
        Button bt1 = new Button("b1");
        Button bt2 = new Button("b2");

        bt2.setStyle("-fx-font: 36 arial; -fx-base: rgb(170,0,0)");
        pane.setLeft(bt1);
        pane.setRight(bt2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}


