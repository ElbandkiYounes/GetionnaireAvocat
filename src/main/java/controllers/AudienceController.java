package controllers;


import DBModels.DBModelAudience;
import classes.SearchedAudience;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AudienceController implements Initializable {
    public TableColumn<SearchedAudience,String> nomColumn;
    public TableColumn<SearchedAudience,String> prenimColumn;
    public TableColumn<SearchedAudience,String> affaireColumn;
    public TableColumn<SearchedAudience,String> noteColumn;
    public TableView<SearchedAudience> tab;
    public DatePicker inputDate;
    public Label lbl_info;
    public Button btnSearch;
    public Button btnRetour;
    private final DBModelAudience DBMAUD = new DBModelAudience();
    private void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }


    @FXML
    void retour(ActionEvent event) throws IOException {
        StagesSwitcher.switchStage(event, "Home",1440,850);
    }

    @FXML
    void search() {
        if (inputDate.getValue() == null) {
            lbl_info.setText("Veuillez inseret une date!");
            System.out.println("Insert Date!");
        } else {
            lbl_info.setText("");
            DBModelAudience.SearchedAudience(inputDate.getValue().toString());
            nomColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("nom"));
            prenimColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("prenom"));
            affaireColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("TypeAffaire"));
            noteColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("etat"));
            tab.setItems(DBModelAudience.getSearchedAudience());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHoverEffect(btnRetour);
        setHoverEffect(btnSearch);
        //Inserting Exesting Data
        try {
            DBModelAudience.getExestingAudience();
        } catch (Exception e) {
            System.out.println(e+"");
        }
        nomColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("nom"));
        prenimColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("prenom"));
        affaireColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("TypeAffaire"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<SearchedAudience,String>("etat"));
        tab.setItems(DBModelAudience.getSearchedAudience());

    }
}