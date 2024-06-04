package classes;

import java.util.Date;

public class Audience {
    private Affaire affaire;
    private int idAudience;
    private Date dateAudience;
    private String note;
    private  String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Audience(Affaire affaire, int idAudience, Date dateAudience, String note, String etat) {
        this.affaire = affaire;
        this.idAudience = idAudience;
        this.dateAudience = dateAudience;
        this.note = note;
        this.etat = etat;
    }

    public int getIdAudience() {
        return idAudience;
    }

    public void setIdAudience(int idAudience) {
        this.idAudience = idAudience;
    }



    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Date getDateAudience() {
        return dateAudience;
    }

    public void setDateAudience(Date dateAudience) {
        this.dateAudience = dateAudience;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
