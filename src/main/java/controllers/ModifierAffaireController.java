package controllers;

import DBModels.DBModelAffaire;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifierAffaireController implements Initializable {
    public TextField nAffaire;
    public TextField tribunale;
    public TextField typeAffaire;
    public TextField typeClient;
    public DatePicker dateAffaire;
    public ChoiceBox<String> etatAffaire;
    private String[] etats= {"Terminé","En attente","En cours"};

    public TextArea note;
    public Label error;
    public Button retourButton;
    public Button ajouterButton;
    public Button effacerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(ajouterButton);
        setHoverEffect(retourButton);
        setHoverEffect(effacerButton);
        etatAffaire.getItems().addAll(etats);
        etatAffaire.setValue(ConsulterClientController.getSelectedAffaire().getEtat());
        dateAffaire.setValue(LocalDate.parse(ConsulterClientController.getSelectedAffaire().getDateCreation().toString()));
        note.setText(ConsulterClientController.getSelectedAffaire().getNote());
        typeAffaire.setText(ConsulterClientController.getSelectedAffaire().getTypeAffaire());
        nAffaire.setText(ConsulterClientController.getSelectedAffaire().getIdAffaire());
        typeClient.setText(ConsulterClientController.getSelectedAffaire().getTypeClient());
        tribunale.setText(ConsulterClientController.getSelectedAffaire().getTribunale());

    }


    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    public void effacer(ActionEvent event) {
        note.clear();
        error.setText("");
        typeClient.clear();
        typeAffaire.clear();
        tribunale.clear();

    }
    public void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
    }

    public void modifierAffaire(ActionEvent event) throws IOException {
        if (tribunale.getText().isEmpty()
                || typeAffaire.getText().isEmpty()
                || typeClient.getText().isEmpty()){
            error.setText("Champ Important Non Inserer");
        }else {
            DBModelAffaire.editAffaire(ConsulterClientController.getSelectedAffaire().getIdAffaire()
                    ,tribunale.getText(),typeAffaire.getText(),typeClient.getText(),
                    dateAffaire.getValue().toString(),note.getText(),etatAffaire.getValue());
            StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);

        }


    }
}
