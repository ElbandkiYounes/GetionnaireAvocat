package classes;

import java.util.Date;

public class Affaire {
    private String idAffaire;
    private Client client;
    private String tribunale;
    private String etat;
    private String typeAffaire;
    private String typeClient;
    private Date dateCreation;
    private String note;

    public String getIdAffaire() {
        return idAffaire;
    }

    public void setIdAffaire(String idAffaire) {
        this.idAffaire = idAffaire;
    }

    public Affaire(Client client, String idAffaire,
                   String tribunale, String etat, String typeAffaire,
                   String typeClient, Date dateCreation, String note) {
        this.idAffaire = idAffaire;
        this.client = client;
        this.tribunale = tribunale;
        this.etat = etat;
        this.typeAffaire = typeAffaire;
        this.typeClient = typeClient;
        this.dateCreation = dateCreation;
        this.note = note;
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTribunale() {
        return tribunale;
    }

    public void setTribunale(String tribunale) {
        this.tribunale = tribunale;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypeAffaire() {
        return typeAffaire;
    }

    public void setTypeAffaire(String typeAffaire) {
        this.typeAffaire = typeAffaire;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = this.note;
    }
}
