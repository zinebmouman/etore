package com.JAVA.Beans;

public class ClientBean {

    private Long id;
    private String nom;
    private String adress;
    private String contact; // Change to String
    private String email; 
    private String passwrd;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPasswrd() { // Change method name
        return passwrd;
    }

    public void setPasswrd(String passwrd) { // Change method name
        this.passwrd = passwrd;
    }

    public String getContact() { // Change method name
        return contact;
    }

    public void setContact(String contact) { // Change to String
        this.contact = contact;
    }
}
