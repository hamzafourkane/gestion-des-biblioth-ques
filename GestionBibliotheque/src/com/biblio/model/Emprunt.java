package com.biblio.model;

import java.sql.Date;

public class Emprunt {
    private int id;
    private String userName;
    private String livreTitre; 
    private Date dateEmprunt;
    private Date dateRetour;
    private boolean retourne;

    public Emprunt(int id, String userName, String livreTitre, Date dateEmprunt, Date dateRetour, boolean retourne) {
        this.id = id;
        this.userName = userName;
        this.livreTitre = livreTitre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.retourne = retourne;
    }

    
    public int getId() { return id; }
    public String getUserName() { return userName; }
    public String getLivreTitre() { return livreTitre; }
    public Date getDateEmprunt() { return dateEmprunt; }
    public Date getDateRetour() { return dateRetour; }
    public boolean isRetourne() { return retourne; }
}