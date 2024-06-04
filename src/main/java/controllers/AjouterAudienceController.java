package controllers;

import DBModels.DBModelAudience;
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
import java.util.ResourceBundle;

public class AjouterAudienceController implements Initializable {

    public TextArea note;
    public ChoiceBox<String> etatAudience;
    public DatePicker dateAudience;
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnEffacer;

    @FXML
    private Button btnRetour;
    private String[] etats= {"Confirmé","Annulé","Passé"};

    @FXML
    void AjouterAudience(ActionEvent event) throws IOException {
        DBModelAudience.addAudience(ConsulterClientController.getSelectedAffaire().getIdAffaire(),note.getText(),dateAudience.getValue().toString(),etatAudience.getValue());
        StagesSwitcher.switchStage(event,"ConsulterAffaire",1440,850);
    }

    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatAudience.getItems().addAll(etats);
        etatAudience.setValue("Confirmé");
        dateAudience.setValue(LocalDate.now());
        setHoverEffect(btnAjouter);
        setHoverEffect(btnRetour);
        setHoverEffect(btnEffacer);
    }


    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterAffaire",1440,850 );
    }

    public void effacer(ActionEvent event) {
        note.clear();
    }

}
