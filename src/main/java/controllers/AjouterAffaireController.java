package controllers;

import DBModels.DBModelAffaire;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterAffaireController implements Initializable {
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
        etatAffaire.setValue("En cours");
        dateAffaire.setValue(LocalDate.now());

    }
    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    public void effacer(ActionEvent event) {
        note.clear();
        error.setText("");
        nAffaire.clear();
        typeClient.clear();
        typeAffaire.clear();
        tribunale.clear();

    }
    public void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
    }

    public void ajouterAffaire(ActionEvent event) throws IOException {
        if (nAffaire.getText().isEmpty()
                || tribunale.getText().isEmpty()
                || typeAffaire.getText().isEmpty()
                || typeClient.getText().isEmpty()){
            error.setText("Champ Important Non Inserer");
        }else {
            for(int i= 0 ; i< DBModelAffaire.getAffaires().size() ;i++){
                if(DBModelAffaire.getAffaires().get(i).getIdAffaire().trim().equals(nAffaire.getText().trim())){
                    error.setText("N° Doussier Existant");
                    return;
                }
            }
            DBModelAffaire.addAffaire(ClientController.getSelectedClient(),nAffaire.getText().trim(),tribunale.getText().trim(),typeAffaire.getText().trim(),typeClient.getText().trim(),dateAffaire.getValue().toString().trim(),note.getText().trim(),etatAffaire.getValue().trim());
           StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);

        }

    }


}
