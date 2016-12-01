package com.epicodus.airdd.models;

public class User {

    // MEMBER VARIABLES
    private String mUsername = "";
    private String mPassword = "";
    private float mRating = 0f;

    // CONSTRUCTOR
    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    // GETTERS
    public String getUsername() {
        return mUsername;
    }
    public String getPassword() {
        return mPassword;
    }
    public float getRating() {
        return mRating;
    }

    // SETTERS
    public void setUsername(String username) {
        mUsername = username;
    }
    public void setPassword(String password) {
        mPassword = password;
    }
    public void setRating(float rating) {
        mRating = rating;
    }
}
