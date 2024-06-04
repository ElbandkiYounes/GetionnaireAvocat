package DBModels;

import DBConnector.*;

import classes.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kotlin.text.UStringsKt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public  class DBModelClient {
    private static ObservableList<Client> clients = null;
    private ArrayList<String> affaireToDelete=  new ArrayList<String>() ;

    public DBModelClient() {
        clients = FXCollections.observableArrayList();
    }
    public static ObservableList<Client> getClients() {
        return clients;
    }
    public static void  getExestingClients()  {
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT * FROM client";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clients.add(0,new Client(rs.getString("cin"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("tel"), rs.getString("email"),rs.getString("adresse"),rs.getString("note")));
            }
            System.out.println("=>All Clients Data Inserted From Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Inserting All Clients Data From Data Base");
            System.out.println(e);
        }


    }
    public static void getSearchedClient(String nom ,String prenom){
        clients = FXCollections.observableArrayList();
        Connection connection = DBConnector.connectDB();
        try {
            String query = "SELECT * FROM client WHERE LOWER(nom) = ? and Lower(prenom) = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getString("cin"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("tel"), rs.getString("email"),rs.getString("adresse"),rs.getString("note")));
            }
            System.out.println("=>Searched Client Found From  Data Base");
            connection.close();
        } catch (Exception e) {
            System.out.println("=>/PROBLEME/ Whene Serching For Client From Data Base");
            System.out.println(e);
        }


    }
    public static void  addClient(String cin,String nom,String prenom,String adresse ,String telephone,String email,String note)  {
        Connection connection = DBConnector.connectDB();
        try{
            String query = "INSERT INTO client(cin,nom,prenom,tel,email,adresse,note) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,cin);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setString(4,telephone);
            ps.setString(5,email);
            ps.setString(6,adresse);
            ps.setString(7,note);
            ps.execute();
            //clients.add(new Client(cin,nom,prenom,telephone,email,adresse,note));
            connection.close();
            System.out.println("=> Client Added To Data Base");
        }catch(Exception e){
            System.out.println("=>/PROBLEM/ In Adding Client To Data Base");
            System.out.println(e);
        }
    }
    public static void editClient(String cin ,String nom,String prenom,String adresse ,String telephone,String email,String note){
        Connection connection = DBConnector.connectDB();
       try {
           String query = "UPDATE client SET  nom = ? , prenom = ? , tel = ? , email = ? , adresse = ? , note = ? Where cin = ?";
           PreparedStatement ps = connection.prepareStatement(query);
           ps.setString(1,nom);
           ps.setString(2,prenom);
           ps.setString(3,telephone);
           ps.setString(4,email);
           ps.setString(5,adresse);
           ps.setString(6,note);
           ps.setString(7,cin);
           ps.execute();
           for(int i=0;i<clients.size();i++){
               if(Objects.equals(clients.get(i).getCin(), cin)){
                   clients.get(i).setAdresse(adresse);
                   clients.get(i).setNom(nom);
                   clients.get(i).setPrenom(prenom);
                   clients.get(i).setNote(note);
                   clients.get(i).setTel(telephone);
                   clients.get(i).setEmail(email);
                   break;
               }
           }
           connection.close();
           System.out.println("=> Client Edited From Data Base");
       }catch (Exception e){
           System.out.println("=> /PROBLEME/ Editing Client From Data Base");
           System.out.println(e);
       }
    }

    public void deleteClient(String cin) {
        Connection connection = DBConnector.connectDB();
        System.out.println(cin);
        try{
            //deleting rdvs
            String query1 = "DELETE FROM rdv WHERE cin = ?";
            PreparedStatement ps1 = connection.prepareStatement(query1);
            ps1.setString(1,cin);
            ps1.execute();
            System.out.println("RDVS DELETED");
            //getting affaire to delete
            String query2 = "SELECT idAffaire FROM affaire WHERE cin = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setString(1,cin);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                affaireToDelete.add(rs2.getString("idAffaire"));
            }
            for(int i = 0 ;i <affaireToDelete.size();i++){
                String query3 = "DELETE FROM audience WHERE idAffaire = ?";
                PreparedStatement ps3 = connection.prepareStatement(query3);
                ps3.setString(1,affaireToDelete.get(i));
                ps3.execute();
                System.out.println("Audience Deleted Conserning  "+cin+":"+affaireToDelete.get(i));
                String query4 = "DELETE FROM affaire WHERE idAffaire = ?";
                PreparedStatement ps4 = connection.prepareStatement(query4);
                ps4.setString(1,affaireToDelete.get(i));
                ps4.execute();
            }
            System.out.println("Affaires Deleted");
            String query5 = "DELETE FROM client  WHERE cin = ?";
            PreparedStatement ps5 = connection.prepareStatement(query5);
            ps5.setString(1,cin);
            ps5.execute();



            System.out.println("Client Deleted From Data Base");
        }catch (Exception e){
            System.out.println("/PROBLEME/ Deleting Client From Data Base");
            System.out.println(e+"");

        }
    }
}



