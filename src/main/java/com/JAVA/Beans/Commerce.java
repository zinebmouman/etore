package com.JAVA.Beans;

public class Commerce {
    private Long id_commerce;
    private String nom;
    private String adresse;
    private String contact;
    private String type_commerce;
    private boolean hasOrders;

    // Constructeur
    public Commerce(Long id_commerce, String nom, String adresse, String contact, String type_commerce) {
        this.id_commerce = id_commerce;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.type_commerce = type_commerce;
    }

    // Getters et Setters
    public Long getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(Long id_commerce) {
        this.id_commerce = id_commerce;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType_commerce() {
        return type_commerce;
    }

    public void setType_commerce(String type_commerce) {
        this.type_commerce = type_commerce;
    }
    public boolean isHasOrders() {
        return hasOrders;
    }

    public void setHasOrders(boolean hasOrders) {
        this.hasOrders = hasOrders;
    }
}
