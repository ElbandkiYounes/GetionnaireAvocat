package controllers;


import DBModels.DBModelAffaire;
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

public class ModifierAudienceController implements Initializable {

    public TextArea noteAudience;
    public DatePicker dateAudience;
    public ChoiceBox<String> etatAudience;
    private final String[] etats= {"Confirmé","Annulé","Passé"};
    @FXML
    private Button btnEffacer;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnRetour;

    @FXML
    void effacer(ActionEvent event) {
        noteAudience.clear();
    }

    @FXML
    void modifierAudience(ActionEvent event) throws IOException {
        DBModelAudience.editAudience(ConsulterAffaireController.getSelectedAudience().getIdAudience()
                ,dateAudience.getValue().toString(),noteAudience.getText(),etatAudience.getValue() );
        StagesSwitcher.switchStage(event,"ConsulterAffaire",1440,850);
        System.out.println("=> Modification Bien effectue");
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterAffaire",1440,850);
    }
    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnEffacer);
        setHoverEffect(btnModifier);
        setHoverEffect(btnRetour);
        etatAudience.getItems().addAll(etats);
        etatAudience.setValue(ConsulterAffaireController.getSelectedAudience().getEtat());
        dateAudience.setValue(LocalDate.parse(ConsulterAffaireController.getSelectedAudience().getDateAudience().toString()));
        noteAudience.setText(ConsulterAffaireController.getSelectedAudience().getNote());
    }
}
