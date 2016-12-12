package com.epicodus.airdd.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Game {

    // STATIC VARIABLES
    static ArrayList<Game> ALL_GAMES;

    // MEMBER VARIABLES
    String mHostID = null;
    String mDMID = null;
    List<String> mPlayerIDs = new ArrayList<>();
    String mTitle = "";
    String mDescription = "";
    String mLocation = "";
    String mDateTime = "";

    // CONSTRUCTORS
    public Game(String hostID, boolean ownerDM, String title, String description, String location, String dateTime) {
        mHostID = hostID;
        if(ownerDM)
            mDMID = hostID;
        else
            mPlayerIDs.add(hostID);
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
    public String getHost() {
        return mHostID;
    }
    public String getDM() {
        return mDMID;
    }
    public List<String> getPlayers() {
        return mPlayerIDs;
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
    public void setHost(String hostID) { mHostID = hostID; }
    public void setDM(String dmID) { mDMID = dmID; }
    public void setPlayers(List<String> playerIDs) { mPlayerIDs = playerIDs;}
    public void addPlayers(String playerID) { mPlayerIDs.add(playerID);}
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
