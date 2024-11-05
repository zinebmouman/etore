package com.JAVA.Beans;

/**
 * Livreur.java
 * Cette classe modèle représente une entité Livreur.
 */
public class Livreur {
    protected Long idLivreur;      // Identifiant du livreur
    protected String nom;          // Nom du livreur
    protected String statut;       // Statut du livreur
    protected String localisation; // Localisation du livreur

    // Constructeur par défaut
    public Livreur() {
        super();
    }

    // Constructeur avec paramètres (sans identifiant)
    public Livreur(String nom, String statut, String localisation) {
        super();
        this.nom = nom;
        this.statut = statut;
        this.localisation = localisation;
    }

    // Constructeur avec tous les paramètres
    public Livreur(Long idLivreur, String nom, String statut, String localisation) {
        super();
        this.idLivreur = idLivreur;
        this.nom = nom;
        this.statut = statut;
        this.localisation = localisation;
    }

    // Getters et Setters
    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    // Méthode toString pour afficher les informations du livreur
    @Override
    public String toString() {
        return "Livreur [idLivreur=" + idLivreur + ", nom=" + nom + ", statut=" + statut + ", localisation=" + localisation + "]";
    }
}
