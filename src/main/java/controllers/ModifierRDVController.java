package controllers;

import DBModels.DBModelClient;
import DBModels.DBModelRDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ModifierRDVController implements Initializable {

    public TextArea noteRDV;
    public DatePicker dateRDV;
    public ChoiceBox<String> etatRDV;
    private final String[] etats= {"Confirmé","Annulé","Passé"};
    public Button btnEffacer;
    public Button btnModifier;
    public Button btnRetour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        etatRDV.getItems().addAll(etats);
        etatRDV.setValue(ConsulterClientController.getSelectedRDV().getEtat());
        dateRDV.setValue(LocalDate.parse(ConsulterClientController.getSelectedRDV().getDateRdv().toString()));
        noteRDV.setText(ConsulterClientController.getSelectedRDV().getNote());
        setHoverEffect(btnEffacer);
        setHoverEffect(btnModifier);
        setHoverEffect(btnRetour);
    }
    public void modifierClinet(ActionEvent event) throws IOException {
        DBModelRDV.editRDV(ConsulterClientController.getSelectedRDV().getIdRDV()
                ,dateRDV.getValue().toString(),noteRDV.getText(),etatRDV.getValue() );
        StagesSwitcher.switchStage(event,"ConsulterClient",1440,850);
        System.out.println("=> Modification Bien effectue");
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
