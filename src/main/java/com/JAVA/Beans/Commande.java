package com.JAVA.Beans;

import java.util.Date;

public class Commande {
    private Long id_commande;
    private Date date_commande;
    private String etat;
    private Long id_client;
    private Long id_commerce;
    private Long id_produit;
    private int quantite;  
    private Produit produit;

    

    // Constructeur
 
    public Commande(Long id_commande, Date date_commande, String etat, Long id_client, Long id_commerce, Long id_produit, int quantite) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.etat = etat;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.id_commerce = id_commerce;
        this.quantite = quantite;
    }

    public Commande() {
		// TODO Auto-generated constructor stub
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

    public Long getid_client () {
        return id_client;
    }

    public void setid_client(Long id_client) {
        this.id_client = id_client;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public Long getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(Long id_commerce) {
        this.id_commerce = id_commerce;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
