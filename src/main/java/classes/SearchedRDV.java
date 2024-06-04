package classes;

public class SearchedRDV {
    private String nom;
    private String prenom;
    private String etat;
    private String note;

    public SearchedRDV(String nom, String prenom, String etat, String note) {
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
