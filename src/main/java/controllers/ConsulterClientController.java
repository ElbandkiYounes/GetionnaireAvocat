package controllers;

import DBModels.DBModelAffaire;
import DBModels.DBModelRDV;
import classes.Affaire;
import classes.Client;
import classes.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;

public class ConsulterClientController implements Initializable {
    public TableColumn<RDV, Date> dateRDVColumn;
    public TableColumn<RDV , String> etatRDVColumn;
    public TableColumn<RDV , String> noteRDVColumn;
    public TableColumn<Affaire , String> nDossierAffaireColumn;
    public TableColumn<Affaire , String> typeAffaireColumn;
    public TableColumn<Affaire , String> etatAffaireColumn;
    public TableColumn<Affaire , String> noteAffaireColumn;
    public Button btnAffaireModifier;
    public Button btnAffaireConsulter;
    public Button btnRDVModifier;
    public  Button btnRetour;
    public Button btnRDVajouter;
    public Button btnAffaireAjouter;
    private Client selectedClient = ClientController.getSelectedClient();
    public TableView<Affaire> tabAffaire;
    public TableView<RDV> tabRDV;
    public Label email;
    public Label telephone;
    public Label adresse;
    public Label prenom;
    public Label nom;
    public Label cin;
    private DBModelRDV DBMR = new DBModelRDV();
    private DBModelAffaire DBMA = new DBModelAffaire();
    private static RDV selectedRDV;
    private static Affaire selectedAffaire;
    public static RDV getSelectedRDV(){
        return selectedRDV;
    }
    public static Affaire getSelectedAffaire(){
        return selectedAffaire;
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"Client",1440,850);
    }
    private void setButtonHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cin.setText(selectedClient.getCin());
        nom.setText(selectedClient.getNom());
        prenom.setText(selectedClient.getPrenom());
        email.setText(selectedClient.getEmail());
        adresse.setText(selectedClient.getAdresse());
        telephone.setText(selectedClient.getTel());

        setButtonHoverEffect(btnRetour);
        setButtonHoverEffect(btnRDVModifier);
        setButtonHoverEffect(btnRDVajouter);
        setButtonHoverEffect(btnAffaireModifier);
        setButtonHoverEffect(btnAffaireAjouter);
        setButtonHoverEffect(btnAffaireConsulter);

        //Inserting Exesting RDVs Data
        try {
            DBModelRDV.getExestingClientRDVs(selectedClient);
        } catch (Exception e) {
            System.out.println(e+"RDV");
        }

        try {
            DBModelAffaire.getExestingClientAffaires(selectedClient);
        } catch (Exception e) {
            System.out.println(e+"Affaire");
        }

        //Linking TableView and ObservableList
        dateRDVColumn.setCellValueFactory(new PropertyValueFactory<RDV, Date>("dateRdv"));
        etatRDVColumn.setCellValueFactory(new PropertyValueFactory<RDV,String>("etat"));
        noteRDVColumn.setCellValueFactory(new PropertyValueFactory<RDV,String>("note"));
        tabRDV.setItems(DBMR.getRDVs());

        etatAffaireColumn.setCellValueFactory(new PropertyValueFactory<Affaire,String>("etat"));
        noteAffaireColumn.setCellValueFactory(new PropertyValueFactory<Affaire,String>("note"));
        typeAffaireColumn.setCellValueFactory(new PropertyValueFactory<Affaire,String>("typeAffaire"));
        nDossierAffaireColumn.setCellValueFactory(new PropertyValueFactory<Affaire,String>("idAffaire"));
        tabAffaire.setItems(DBMA.getAffaires());

    }
    public void modifierRDVEnabled(MouseEvent mouseEvent) {
        selectedRDV = tabRDV.getSelectionModel().getSelectedItem();
        btnRDVModifier.setDisable(false);

    }
    public void modifierConsulterAffaireEnabled(MouseEvent mouseEvent) {
        selectedAffaire = tabAffaire.getSelectionModel().getSelectedItem();
        btnAffaireConsulter.setDisable(false);
        btnAffaireModifier.setDisable(false);

    }
    public void switchToAjouterRDV(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"AjouterRDV",1440,850);
    }

    public void switchToModifierRDV(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ModifierRDV",1440,850);

    }
    public void switchToAjouterAffaire(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"AjouterAffaire",1440,850);
    }
    public void switchToModifierAffaire(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ModifierAffaire",1440,850);
    }
    public void switchToConsulterAffaire(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterAffaire",1440,850);
    }
}

