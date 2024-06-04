package DBModels;

import DBConnector.DBConnector;
import classes.Client;
import classes.RDV;
import classes.SearchedRDV;
import controllers.ClientController;
import controllers.StagesSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Objects;


public class DBModelRDV {

    private static ObservableList<RDV> RDVs = null;

    private static ObservableList<SearchedRDV> searchedRDVS = null;

    public DBModelRDV() {
        RDVs = FXCollections.observableArrayList();
        searchedRDVS = FXCollections.observableArrayList();
    }

    public ObservableList<RDV> getRDVs() {
        return RDVs;
    }
    public static void  getExestingClientRDVs(Client client)  {
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT * FROM rdv WHERE rdv.cin = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,client.getCin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RDVs.add(0,new RDV(client,rs.getString("etat"), rs.getString("note"),
                        Date.valueOf(rs.getString("dateRdv")),rs.getInt("idRdv")));

            }

            System.out.println("=>All Client RDVs Data Inserted From Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ In Inserting All Client RDVs Data From Data Base");
            System.out.println(e);
        }
    }
    public static void addRDV(String cin, String note, String dateRDV, String etatRDV) {
        Connection connection = DBConnector.connectDB();
        try{
            String query = "INSERT INTO rdv(cin,note,dateRdv,etat) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,cin);
            ps.setString(2,note);
            ps.setString(3,dateRDV);
            ps.setString(4,etatRDV);
            ps.execute();
            connection.close();
            System.out.println("=> RDV Added To Data Base");
        }catch(Exception e){
            System.out.println("=>/PROBLEM/ In Adding RDV To Data Base");
            System.out.println(e);
        }
    }
    public static void editRDV(int idRDV, String dateRDV, String note, String etat ){
        Connection connection = DBConnector.connectDB();
        try {
            String query = "UPDATE rdv SET dateRdv = ? , etat = ? , note = ? Where idRdv = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,dateRDV);
            ps.setString(2,etat);
            ps.setString(3,note);
            ps.setInt(4,idRDV);
            ps.execute();
            for(int i=0;i<RDVs.size();i++){
                if(Objects.equals(RDVs.get(i).getIdRDV(), idRDV)){
                    RDVs.get(i).setNote(note);
                    RDVs.get(i).setDateRdv(Date.valueOf(dateRDV));
                    RDVs.get(i).setEtat(etat);
                    RDVs.get(i).setIdRDV(idRDV);
                    break;
                }
            }
            connection.close();
            System.out.println("=> RDV Edited From Data Base");
        }catch (Exception e){
            System.out.println("=> /PROBLEME/ Editing RDV From Data Base");
            System.out.println(e);
        }
    }
    public static void SearchedRDV(String rdvDATE) {
        Connection connection = DBConnector.connectDB();
        searchedRDVS.clear();
        try {
            String query = "SELECT client.nom,client.prenom,rdv.etat,rdv.note FROM client,rdv WHERE client.cin = rdv.cin AND rdv.dateRdv =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, rdvDATE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                searchedRDVS.add(new SearchedRDV(rs.getString("nom"), rs.getString("prenom"), rs.getString("etat"), rs.getString("note")));
                System.out.println("All searcher RDVS inserted");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Inserting All Data From Data Base");
            System.out.println(e);
        }
    }

    public static ObservableList<SearchedRDV> getSearchedRDVS() {
        return searchedRDVS;
    }

    public static void getExestingRDVS() {
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT client.nom,client.prenom,rdv.etat,rdv.note FROM client,rdv WHERE client.cin = rdv.cin ORDER BY rdv.dateRdv";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                searchedRDVS.add(new SearchedRDV(rs.getString("nom"), rs.getString("prenom"), rs.getString("etat"), rs.getString("note")));
            }
            System.out.println("=>All Searched RDVS Data Inserted From Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Inserting All Data From Data Base");
            System.out.println(e);
        }
    }
}
