package controllers;

import DBModels.DBModelAffaire;
import DBModels.DBModelClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AjouterClientController implements Initializable {

    public TextField cin;
    public TextField nom;
    public TextField prenom;
    public TextField adresse;
    public TextField telephone;
    public TextField email;
    public TextArea note;
    public Label error;
    public Button btnAjouter;
    public Button btnEffacer;
    public Button btnRetour;


    public void ajouterClinet(ActionEvent event) throws IOException {
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
            for(int i = 0; i< DBModelClient.getClients().size() ; i++){
                if(DBModelClient.getClients().get(i).getCin().trim().equals(cin.getText().trim())){
                    error.setText("Client Existant");
                    return;
                }
            }
        DBModelClient.addClient(cin.getText().trim(),nom.getText().trim(),prenom.getText().trim(),telephone.getText().trim(),email.getText().trim(),adresse.getText().trim(),note.getText().trim());
        StagesSwitcher.switchStage(event,"Client",1440,850);
    }

    public void effacer(ActionEvent event) {
        nom.clear();
        prenom.clear();
        email.clear();
        note.clear();
        telephone.clear();
        adresse.clear();
        cin.clear();
        error.setText("");
    }

    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }
    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"Client",1440,850);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnAjouter);
        setHoverEffect(btnEffacer);
        setHoverEffect(btnRetour);
        
    }
}
