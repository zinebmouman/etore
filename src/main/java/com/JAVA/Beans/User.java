package com.JAVA.Beans;

public class User {
    private Long userId;
    private String email;
    private String password;
    private int type;

    public User() {}

    public User(Long userId, String email, String password, int type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    // Getters et Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long long1) {
        this.userId = long1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
