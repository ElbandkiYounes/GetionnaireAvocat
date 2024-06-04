package controllers;

import avocatg1.avocat.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StagesSwitcher {
    public static void switchStage(ActionEvent event,String fxml,int x,int y) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        Scene scene = new Scene(fxmlLoader.load(), x, y);
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
