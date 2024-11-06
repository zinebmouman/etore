package com.JAVA.Beans;

public class ClientBean {

    private Long id_client;
    private String nom;
    private String adress;
    private String contact; // Change to String
    private String email; 
    private String password;

    // Getters and Setters
    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) { // Change method name
        this.adress = adress;
    }

    public String getEmail() { // Change method name
        return email;
    }

    public void setEmail(String email) { // Change method name
        this.email = email;
    }

    public String getpassword() { // Change method name
        return password;
    }

    public void setpassword(String password) { // Change method name
        this.password = password;
    }

    public String getContact() { // Change method name
        return contact;
    }

    public void setContact(String contact) { // Change to String
        this.contact = contact;
    }
}
