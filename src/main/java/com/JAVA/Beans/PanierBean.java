package com.JAVA.Beans;

public class PanierBean {
    private int id;
    private long clientId;
    private int produitId;
  
    private int quantite;
    private Produit produit;

    // Constructeur par défaut
    public PanierBean() {
        this.quantite = 1; // Quantité par défaut
    }

    // Constructeur avec tous les attributs sauf id (géré par la base de données)
    public PanierBean( long clientId, int produitId, int quantite) {
        this.clientId = clientId;
       
        this.produitId = produitId;
       
        this.quantite = quantite;
      
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
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
    @Override
    public String toString() {
        return "PanierBean [id=" + id + ", clientId=" + clientId + ", produitId=" + produitId + 
               ", quantite=" + quantite + ", produit=" + produit + "]";
    }
}