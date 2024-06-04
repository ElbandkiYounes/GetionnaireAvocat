package controllers;

import DBModels.DBModelClient;
import classes.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierClientController implements Initializable {

    public TextField cin;
    public TextField nom;
    public TextField prenom;
    public TextField adresse;
    public TextField telephone;
    public TextField email;
    public TextArea note;
    public Label error;
    public String oldCin = ClientController.getSelectedClient().getCin();
    public Client oldClient = ClientController.getSelectedClient() ;
    public Button btnRetour;
    public Button btnEffacer;
    public Button btnModifier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnEffacer);
        setHoverEffect(btnRetour);
        setHoverEffect(btnModifier);
        cin.setText(oldCin);
        cin.setDisable(true);
        nom.setText(oldClient.getNom());
        prenom.setText(oldClient.getPrenom());
        email.setText(oldClient.getEmail());
        telephone.setText(oldClient.getTel());
        note.setText(oldClient.getNote());
        adresse.setText(oldClient.getAdresse());
    }
    public void modifierClinet(ActionEvent event) throws IOException {
        //important data (all excepte note)
        if(cin.getText().isEmpty()
                || nom.getText().isEmpty()
                || prenom.getText().isEmpty()
                ||telephone.getText().isEmpty()
                ||email.getText().isEmpty()
                ||adresse.getText().isEmpty()){
            error.setText("Champ Important Non Inserer");
            return;

        }else
            DBModelClient.editClient(oldCin.trim(),nom.getText().trim(),prenom.getText().trim(),telephone.getText().trim(),email.getText().trim(),adresse.getText().trim(),note.getText().trim());
            StagesSwitcher.switchStage(event,"Client",1440,850);
            System.out.println("=> Modification Bien effectue");
    }

    public void effacer(ActionEvent event) {
        nom.clear();
        prenom.clear();
        email.clear();
        note.clear();
        telephone.clear();
        adresse.clear();
        cin.clear();
    }
    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"Client",1440,850);
    }

    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }


}
