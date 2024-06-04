package classes;

import java.util.Date;

public class RDV {
    private int idRDV;
    private Client client;
    private String etat;
    private String note;
    private Date dateRdv;

    public RDV(Client client, String etat, String note, Date dateRdv,int idRDV) {
        this.idRDV = idRDV;
        this.client = client;
        this.etat = etat;
        this.note = note;
        this.dateRdv = dateRdv;
    }

    public int getIdRDV() {
        return idRDV;
    }

    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }
}
