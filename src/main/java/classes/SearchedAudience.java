package classes;

public class SearchedAudience {
    private String nom;
    private String prenom;
    private String typeAffaire;
    private String etat;

    public SearchedAudience(String nom, String prenom, String typeAffaire, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.typeAffaire = typeAffaire;
        this.etat = etat;
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

    public String getTypeAffaire() {
        return typeAffaire;
    }

    public void setTypeAffaire(String typeAffaire) {
        this.typeAffaire = typeAffaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String note) {
        this.etat = note;
    }
}
