package com.epicodus.airdd.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Game {

    // STATIC VARIABLES
    static ArrayList<Game> ALL_GAMES;

    // MEMBER VARIABLES
    User mHost = null;
    User mDM = null;
    List<User> mPlayers = new ArrayList<>();
    String mTitle = "";
    String mDescription = "";
    String mLocation = "";
    String mDateTime = "";

    // CONSTRUCTORS
    public Game(User host, boolean ownerDM, String title, String description, String location, String dateTime) {
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
    public Game() {    }

    // STATIC METHODS
    public static Game findByTitle(ArrayList<Game> games, String title) {
        for(int i = 0; i < games.size(); i++) {
            if(games.get(i).getTitle().equals(title)) {
                return games.get(i);
            }
        }
        return null;
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
    public String getDateTime() {
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
    public void setDateTime(String dateTime) {
        mDateTime = dateTime;
    }
}
