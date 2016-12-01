package com.epicodus.airdd.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

    // MEMBER VARIABLES
    private User mHost = null;
    private User mDM = null;
    private List<User> mPlayers = new ArrayList<>();
    private String mTitle = "";
    private String mDescription = "";
    private String mLocation = "";
    private Date mDateTime = null;

    // CONSTRUCTOR
    public Game(User host, boolean ownerDM, String title, String description, String location, Date dateTime) {
        mHost = host;
        if(ownerDM)
            mDM = host;
        else
            mPlayers.add(host);
        mTitle = title;
        mDescription = description;
        mLocation = location;
        mDateTime = dateTime;
    }
    public Game(String title) {
        mTitle = title;
    }

    // GETTERS
    public User getHost() {
        return mHost;
    }
    public User getDM() {
        return mDM;
    }
    public List<User> getPlayers() {
        return mPlayers;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    public String getLocation() {
        return mLocation;
    }
    public Date getDateTime() {
        return mDateTime;
    }

    // SETTERS
    public void setHost(User host) {
        mHost = host;
    }
    public void setDM(User dm) {
        mDM = dm;
    }
    public void setPlayers(List<User> players) {
        mPlayers = players;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public void setLocation(String location) {
        mLocation = location;
    }
    public void setDateTime(Date dateTime) {
        mDateTime = dateTime;
    }
}
