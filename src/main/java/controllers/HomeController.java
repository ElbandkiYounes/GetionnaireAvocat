package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private Button btnClient;

    @FXML
    private Button btnRDV;

    @FXML
    private Button btnAudience;

    public void gestionClient(ActionEvent actionEvent) throws IOException {
       StagesSwitcher.switchStage(actionEvent,"Client",1440,850);
    }

    public void gestionRDV(ActionEvent actionEvent) throws IOException{
        StagesSwitcher.switchStage(actionEvent,"RDV",1440,850);
    }

    public void gestionAudience(ActionEvent actionEvent) throws IOException{
        StagesSwitcher.switchStage(actionEvent,"Audience",1440,850);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnClient.setOnMouseEntered(event -> btnClient.setOpacity(0.8));
        btnClient.setOnMouseExited(event -> btnClient.setOpacity(1.0));

        btnRDV.setOnMouseEntered(event -> btnRDV.setOpacity(0.8));
        btnRDV.setOnMouseExited(event -> btnRDV.setOpacity(1.0));

        btnAudience.setOnMouseEntered(event -> btnAudience.setOpacity(0.8));
        btnAudience.setOnMouseExited(event -> btnAudience.setOpacity(1.0));
    }
}

