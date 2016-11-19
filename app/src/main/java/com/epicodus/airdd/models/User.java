package com.epicodus.airdd.models;

public class User {
    private String mUsername = "";
    private String mPassword = "";
    private float mRating = 0f;

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }
}
