package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        Button bt1 = new Button("B1");
        Button bt2 = new Button("B2");
        root.setLeft(bt1);
        root.setRight(bt2);
        bt1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            @FXML
            public void handle(ActionEvent eventBt1){
                System.out.println("inside event handler at bt1");
            }
        });
        bt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            @FXML
            public void handle(ActionEvent eventBt2) {
                System.out.println("inside eventhandler at bt2");
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
