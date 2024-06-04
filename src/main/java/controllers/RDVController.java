package controllers;

import DBModels.DBModelRDV;
import classes.SearchedRDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RDVController implements Initializable {

    public TableColumn<SearchedRDV,String > nomColumn;
    public TableColumn<SearchedRDV,String > prenomColumn;
    public TableColumn<SearchedRDV,String > etatColumn;
    public TableColumn<SearchedRDV,String > noteColumn;
    public Button btnRetour;
    public Button btnSearch;
    @FXML
    private DatePicker inputDate;

    @FXML
    private TableView<SearchedRDV> tab;

    @FXML
    private Label lbl_info;
    private DBModelRDV DBMRDV = new DBModelRDV();

    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event, "Home",1440,850);
    }
    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    @FXML
    void search() {
        if (inputDate.getValue() == null) {
            lbl_info.setText("Veuillez inseret une date!");
            System.out.println("Insert Date!");
        } else {
            lbl_info.setText("");
            DBModelRDV.SearchedRDV(inputDate.getValue().toString());
            nomColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("prenom"));
            etatColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("etat"));
            noteColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("note"));
            tab.setItems(DBModelRDV.getSearchedRDVS());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inserting Exesting Data
        try {
            DBModelRDV.getExestingRDVS();
        } catch (Exception e) {
            System.out.println(e+"");
        }
        setHoverEffect(btnRetour);
        setHoverEffect(btnSearch);
        nomColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("prenom"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("etat"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<SearchedRDV,String>("note"));
        tab.setItems(DBModelRDV.getSearchedRDVS());
    }
}