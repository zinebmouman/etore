package com.JAVA.Beans;

public class PanierBean {

	
    private int id;
    private int utilisateurId;
    private String produitsJSON;  // JSON format of products and quantities

    // Getters et setters
    public int getId() {
    	return id;
    	}
    public void setId(int id) {
    	this.id = id;
    	}

    public int getUtilisateurId() { 
    	return utilisateurId;
    	}
    public void setUtilisateurId(int utilisateurId){
    	this.utilisateurId = utilisateurId; 
    	}

    public String getProduitsJSON() {
    	return produitsJSON; 
    	}
    public void setProduitsJSON(String produitsJSON) {
    	this.produitsJSON = produitsJSON; 
    	}
}
