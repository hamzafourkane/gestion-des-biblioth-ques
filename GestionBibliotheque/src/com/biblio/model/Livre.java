package com.biblio.model;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private boolean disponible;
    private String imagePath;

    public Livre(int id, String titre, String auteur, String isbn, boolean disponible, String imagePath) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.disponible = disponible;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    public String getImagePath() { return imagePath; }
}