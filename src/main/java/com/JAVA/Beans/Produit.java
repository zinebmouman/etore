package com.JAVA.Beans;

public class Produit {
    private Long idProduit;
    private String nom;
    private double prix;
    private String description;
    private Long idCommerce;
    private String image;

    // Constructeurs
    public Produit() {}

    public Produit(String nom, double prix , String image, String description, Long idCommerce) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.idCommerce = idCommerce;
        this.image=image;
    }

    public Produit(Long idProduit, String nom ,  String image, double prix, String description, Long idCommerce) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.idCommerce = idCommerce;
    }

    // Getters et Setters
    public Long getIdProduit() {
        return idProduit;
    }
   
    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }
    public String getimage() {
        return image;
    }
    public void  setImage(String image) {
        this.image = image;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdCommerce() {
        return idCommerce;
    }

    public void setIdCommerce(Long idCommerce) {
        this.idCommerce = idCommerce;
    }

    @Override
    public String toString() {
        return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", prix=" + prix + 
               ", description=" + description + ", idCommerce=" + idCommerce + ", image=" + image + "]";
    }
}
