package controllers;

import DBModels.DBModelAffaire;
import DBModels.DBModelAudience;
import classes.Affaire;
import classes.Audience;
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

public class ConsulterAffaireController implements Initializable {

    public Label nDossier;
    public Label tribunale;
    public Label typeAffaire;
    public Label dateCreation;
    public Label typeClient;
    public Label etatAffaire;
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnRetour;

    @FXML
    private TableColumn<Audience, Date> dateAudience;

    @FXML
    private TableColumn<Audience, String> noteAudience;

    @FXML
    private TableView<Audience> tabAudience;
    private Affaire selectedAffaire = ConsulterClientController.getSelectedAffaire();
    private DBModelAudience DBMA = new DBModelAudience();
    public static Audience selectedAudience = null ;
    public static Audience getSelectedAudience(){
        return selectedAudience;
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);

    }

    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnAjouter);
        setHoverEffect(btnModifier);
        setHoverEffect(btnRetour);
        nDossier.setText(selectedAffaire.getIdAffaire());
        tribunale.setText(selectedAffaire.getTribunale());
        typeAffaire.setText(selectedAffaire.getTypeAffaire());
        typeClient.setText(selectedAffaire.getTypeClient());
        etatAffaire.setText(selectedAffaire.getEtat());
        dateCreation.setText(selectedAffaire.getDateCreation().toString());
        try {
            DBModelAudience.getExistingAudienceAffaire(selectedAffaire);
        } catch (Exception e) {
            System.out.println(e + " Audience");
        }
        dateAudience.setCellValueFactory(new PropertyValueFactory<Audience,Date>("dateAudience"));
        noteAudience.setCellValueFactory(new PropertyValueFactory<Audience,String>("note"));
        tabAudience.setItems(DBMA.getAudiences());
    }
    public void modifierAudienceEnabled(MouseEvent mouseEvent) {
        selectedAudience = tabAudience.getSelectionModel().getSelectedItem();
        btnModifier.setDisable(false);

    }
    public void switchToAjouter(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"AjouterAudience",1440,850 );
    }

    public void switchToModifier(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event,"ModifierAudience",1440,850 );
    }


}

