package DBModels;

import DBConnector.DBConnector;
import classes.Affaire;
import classes.Client;
import classes.RDV;
import controllers.ClientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class DBModelAffaire {

    private static ObservableList<Affaire> affaires = null;

    public DBModelAffaire() {
        affaires = FXCollections.observableArrayList();
    }

    public static void addAffaire(Client selectedClient ,String nAffaire, String tribunale , String typeAffaire, String typeClient, String dateAffaire,String note ,String etatAffaire) {
        Connection connection = DBConnector.connectDB();
        try{
            String query = "INSERT INTO affaire VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,nAffaire);
            ps.setString(2,tribunale);
            ps.setString(3,etatAffaire);
            ps.setString(4,typeAffaire);
            ps.setString(5,dateAffaire);
            ps.setString(6,note);
            ps.setString(7,typeClient);
            ps.setString(8,selectedClient.getCin());
            ps.execute();
            connection.close();
            System.out.println("=> Affaire Added To Data Base");

        } catch (Exception e) {
            System.out.println("=>/PROBLEM/ In Adding Affaire To Data Base");
            System.out.println(e);
        }

    }

    public static void editAffaire(String nAffaire ,String tribunale,String typeAffaire,String typeClient ,String dateAffaire,String note,String etatAffaire){
        Connection connection = DBConnector.connectDB();
        try {
            String query = "UPDATE affaire SET  tribunale = ? , etat = ? , typeAffaire = ? , dateCreation = ? , note = ? , typeClient = ? Where idAffaire = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,tribunale);
            ps.setString(2,etatAffaire);
            ps.setString(3,typeAffaire);
            ps.setString(4,dateAffaire);
            ps.setString(5,note);
            ps.setString(6,typeClient);
            ps.setString(7,nAffaire);
            ps.execute();
            for(int i=0;i<affaires.size();i++){
                if(Objects.equals(affaires.get(i).getIdAffaire(), nAffaire)){
                    affaires.get(i).setNote(note);
                    affaires.get(i).setTypeAffaire(typeAffaire);
                    affaires.get(i).setEtat(etatAffaire);
                    affaires.get(i).setTribunale(tribunale);
                    affaires.get(i).setTypeClient(typeClient);
                    affaires.get(i).setDateCreation(Date.valueOf(dateAffaire));
                    break;
                }
            }
            connection.close();
            System.out.println("=> Affaire Edited From Data Base");
        }catch (Exception e){
            System.out.println("=> /PROBLEME/ Editing Affaire From Data Base");
            System.out.println(e);
        }
    }

    public static ObservableList<Affaire> getAffaires() {
        return affaires;
    }
    public static void getExestingClientAffaires(Client client) {
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT * FROM affaire WHERE affaire.cin = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, client.getCin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                affaires.add(0,new Affaire(client, rs.getString("idaffaire"),
                        rs.getString("tribunale"), rs.getString("etat"),
                        rs.getString("typeAffaire"), rs.getString("typeClient"),
                        Date.valueOf(rs.getString("dateCreation")), rs.getString("note")));
            }

            System.out.println("=>All Client Affaires Data Inserted From Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ In Inserting All Client Affaires Data From Data Base");
            System.out.println(e);
        }
    }


}
