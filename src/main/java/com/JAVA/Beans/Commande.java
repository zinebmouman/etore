package com.JAVA.Beans;

import java.util.Date;

public class Commande {
    private Long id_commande;
    private Date date_commande;
    private String etat;
    private Long id_client; // ID du client, doit correspondre à la table Client
    private Long id_commerce; // ID du commerce, doit correspondre à la table Commerce

    // Constructeur
    public Commande(Long id_commande, Date date_commande, String etat, Long id_client, Long id_commerce) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.etat = etat;
        this.id_client = id_client;
        this.id_commerce = id_commerce;
    }

    // Getters et Setters
    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public Long getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(Long id_commerce) {
        this.id_commerce = id_commerce;
    }
}
