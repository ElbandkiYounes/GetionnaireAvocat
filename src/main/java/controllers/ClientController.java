package controllers;

import DBModels.DBModelClient;
import classes.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    //Switching buttons
    public Button btnConsulter;
    public Button btnModifier;
    public Button btnAjouter;

    public  Button retourButton;
    public Button rechercherButton;
    public Label errorPrenom;
    public Label errorNom;
    public Button btnSupprimer;
    @FXML
    private TextField prenomR;
    @FXML
    private TextField nomR;

    @FXML
    private TableColumn<Client, String> noteColumn;
    @FXML
    private TableColumn<Client, String> prenomColumn;
    @FXML
    private TableColumn<Client, String> nomColumn;
    @FXML
    private TableColumn<Client, String> cinColumn;

    @FXML
    private TableView<Client> tab;
    private DBModelClient DBMC = new DBModelClient();
    private static Client selectedClient  ;
    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"Home",1440,850);
    }
    public static Client getSelectedClient(){
        return selectedClient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnSupprimer);
        setHoverEffect(btnConsulter);
        setHoverEffect(btnModifier);
        setHoverEffect(btnAjouter);
        setHoverEffect(rechercherButton);
        setHoverEffect(retourButton);
        //Inserting Exesting Data
        try {
            DBModelClient.getExestingClients();
        } catch (Exception e) {
            System.out.println(e+"");
        }

        //Linking TableView and ObservableList
        cinColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("cin"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("note"));
        tab.setItems(DBMC.getClients());
    }
    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    @FXML
    void rechercher(ActionEvent event) throws SQLException {
        if(nomR.getText().trim().isEmpty() || prenomR.getText().trim().isEmpty()){
            if (prenomR.getText().trim().isEmpty()) errorPrenom.setText("Inserer Le Prenom");
            if(nomR.getText().trim().isEmpty()) errorNom.setText(("Inserer Le Nom"));
        }else{
            errorNom.setText("");
            errorPrenom.setText("");
            DBModelClient.getSearchedClient(nomR.getText().trim().toLowerCase(),prenomR.getText().trim().toLowerCase());
            tab.setItems(DBMC.getClients());
        }

    }
    public void switchToConsulter(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
    }
    public void switchToModifier(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ModifierClient",1440,850);
    }
    public void switchToAjouter(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"AjouterClient",1440,850);
    }
    public void modifierConsulterenabled(MouseEvent mouseEvent) {
         selectedClient = tab.getSelectionModel().getSelectedItem();
         btnModifier.setDisable(false);
         btnConsulter.setDisable(false);
         btnSupprimer.setDisable(false);
    }

    public void deleteClient(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Etes-vous sur de vouloire supprimer ce client");
        alert.getDialogPane().setHeaderText("Confirmation de la suppression");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            DBMC.deleteClient(selectedClient.getCin());
            StagesSwitcher.switchStage(event,"Client",1440,850);
            System.out.println("OK");
        }else if(result.get() == ButtonType.CANCEL){
            System.out.println("Cancel");
        }
    }
}
