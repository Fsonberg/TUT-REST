package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MyMaze");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
        primaryStage.setResizable(false); // Prevents the window from being resized.

    }


    public static void main(String[] args) {
        launch(args);
    }
}
