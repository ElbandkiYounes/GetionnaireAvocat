package DBModels;

import DBConnector.DBConnector;
import classes.Affaire;
import classes.Audience;
import classes.SearchedAudience;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class DBModelAudience {
    private static ObservableList<Audience> audiences = null;
    private static ObservableList<SearchedAudience> searchedAudience=null;

    public DBModelAudience(){audiences = FXCollections.observableArrayList();
        searchedAudience = FXCollections.observableArrayList();}

    public static ObservableList getSearchedAudience() {
        return searchedAudience;
    }
    public ObservableList<Audience> getAudiences() {return audiences;}

    public static void editAudience(int idAudience, String dateAudience, String note, String etat ){
        Connection connection = DBConnector.connectDB();
        try {
            String query = "UPDATE audience SET dateAudience = ? , etat = ? , note = ? Where idAudience = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,dateAudience);
            ps.setString(2,etat);
            ps.setString(3,note);
            ps.setInt(4,idAudience);
            ps.execute();
            for(int i=0;i<audiences.size();i++){
                if(Objects.equals(audiences.get(i).getIdAudience(), idAudience)){
                    audiences.get(i).setNote(note);
                    audiences.get(i).setDateAudience(Date.valueOf(dateAudience));
                    audiences.get(i).setEtat(etat);
                    audiences.get(i).setIdAudience(idAudience);
                    break;
                }
            }
            connection.close();
            System.out.println("=> Audience Edited From Data Base");
        }catch (Exception e){
            System.out.println("=> /PROBLEME/ Editing Audience From Data Base");
            System.out.println(e);
        }
    }

    public static void addAudience(String idAffaire, String note, String dateAudience, String etatAudience) {
        Connection connection = DBConnector.connectDB();
        try{
            String query = "INSERT INTO audience(idAffaire,note,dateAudience,etat) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idAffaire);
            ps.setString(2,note);
            ps.setString(3,dateAudience);
            ps.setString(4,etatAudience);
            ps.execute();
            connection.close();
            System.out.println("=> Audience Added To Data Base");
        }catch(Exception e){
            System.out.println("=>/PROBLEM/ In Adding Audience To Data Base");
            System.out.println(e);
        }
    }

    public static void getExistingAudienceAffaire(Affaire selectedAffaire) {
        Connection connection = DBConnector.connectDB();
        try{
            String query = "SELECT * FROM audience WHERE idAffaire = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,selectedAffaire.getIdAffaire());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                audiences.add(0,new Audience(selectedAffaire,rs.getInt("idAudience"), Date.valueOf(rs.getString("dateAudience")),rs.getString("note"),rs.getString("etat")));
            }
            System.out.println("=>All Audinces Data Inserted From Data Base");
            connection.close();

        }catch(Exception e){
            System.out.println("=>/PROBLEME/ Inserting  All Audinces Data From Data Base");
            System.out.println(e);
        }

    }

    public static void getExestingAudience() {
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT DISTINCT client.nom,client.prenom,affaire.typeAffaire,audience.note FROM client,audience,affaire where affaire.idAffaire=audience.idAffaire AND client.cin = affaire.cin ORDER BY audience.dateAudience;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                searchedAudience.add(new SearchedAudience(rs.getString("nom"), rs.getString("prenom"), rs.getString("typeAffaire"), rs.getString("note")));
            }
            System.out.println("=>All Searched AUDIENCE Data Inserted From Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Inserting All Data From Data Base");
            System.out.println(e);
        }
    }
    public static void SearchedAudience(String AudienceDATE) {
        Connection connection = DBConnector.connectDB();
        searchedAudience.clear();
        try {
            String query = "SELECT DISTINCT client.nom,client.prenom,affaire.typeAffaire,audience.note,dateAudience FROM client,audience,affaire where affaire.idAffaire=audience.idAffaire AND client.cin = affaire.cin AND audience.dateAudience =? ORDER BY audience.dateAudience;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, AudienceDATE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                searchedAudience.add(new SearchedAudience(rs.getString("nom"), rs.getString("prenom"), rs.getString("typeAffaire"), rs.getString("note")));
                System.out.println("All searcher Audience inserted");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Inserting All Data From Data Base");
            System.out.println(e);
        }
    }

}
