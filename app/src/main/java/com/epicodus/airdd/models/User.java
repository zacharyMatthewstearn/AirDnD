package com.epicodus.airdd.models;

import org.parceler.Parcel;

@Parcel
public class User {

    // MEMBER VARIABLES
    String username = "";
    String email = "";
    String password = "";
    String uid = "";

    // CONSTRUCTORS
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {     }

    // GETTERS
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUid() { return uid; }

    // SETTERS
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUid(String uid) {
        username = uid;
    }
}
