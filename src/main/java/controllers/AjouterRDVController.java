package controllers;

import DBModels.DBModelClient;
import DBModels.DBModelRDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterRDVController implements Initializable {
    public TextArea noteRDV;
    public DatePicker dateRDV;
    public ChoiceBox<String> etatRDV;
    public Button btnRetour;
    public Button btnEffacer;
    public Button btnAjouter;
    private String[] etats= {"Confirmé","Annulé","Passé"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatRDV.getItems().addAll(etats);
        etatRDV.setValue("Confirmé");
        dateRDV.setValue(LocalDate.now());
        setHoverEffect(btnAjouter);
        setHoverEffect(btnRetour);
        setHoverEffect(btnEffacer);
    }
    public void ajouterRDV(ActionEvent event) throws IOException {
        DBModelRDV.addRDV(ClientController.getSelectedClient().getCin(),noteRDV.getText(),dateRDV.getValue().toString(),etatRDV.getValue());
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
    }

    public void effacer(ActionEvent event) {
        noteRDV.clear();

    }

    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }


}
