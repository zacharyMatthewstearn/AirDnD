package com.epicodus.airdd.models;

import org.parceler.Parcel;

@Parcel
public class User {

    // MEMBER VARIABLES
    String mUsername = "";
    String mPassword = "";
//    String mAddress = "";

    // CONSTRUCTORS
    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public User() {     }

    // GETTERS
    public String getUsername() {
        return mUsername;
    }
    public String getPassword() {
        return mPassword;
    }
//    public String getAddress() {return mAddress; }

    // SETTERS
    public void setUsername(String username) {
        mUsername = username;
    }
    public void setPassword(String password) {
        mPassword = password;
    }
//    public void setAddress(String address) { mAddress = address; }
}
