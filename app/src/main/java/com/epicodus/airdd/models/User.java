package com.epicodus.airdd.models;

import org.parceler.Parcel;

@Parcel
public class User {

    // MEMBER VARIABLES
    String mUsername = "";
    String mEmail = "";
    String mPassword = "";
    String mUid = "";

    // CONSTRUCTORS
    public User(String username, String email, String password) {
        mUsername = username;
        mEmail = email;
        mPassword = password;
    }

    public User() {     }

    // GETTERS
    public String getUsername() {
        return mUsername;
    }
    public String getEmail() {
        return mEmail;
    }
    public String getPassword() {
        return mPassword;
    }
    public String getUid() {
        return mUid;
    }

    // SETTERS
    public void setUsername(String username) {
        mUsername = username;
    }
    public void setEmail(String email) {
        mEmail = email;
    }
    public void setPassword(String password) {
        mPassword = password;
    }
    public void setUid(String uid) {
        mUsername = uid;
    }
}
