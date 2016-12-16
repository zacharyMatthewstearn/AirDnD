package com.epicodus.airdd.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Game {

    // STATIC VARIABLES
    static ArrayList<Game> ALL_GAMES;

    // MEMBER VARIABLES
    String hostId = "";
    String dmId = "";
    List<String> playerIds = new ArrayList<>();
    String title = "";
    String description = "";
    String location = "";
    String dateTime = "";
    boolean hostDm = true;

    // CONSTRUCTORS
    public Game(String title, String description, String location, String dateTime, boolean hostDm) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.hostDm = hostDm;
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
    public String getHostId() {
        return hostId;
    }
    public String getDmId() {
        return dmId;
    }
    public List<String> getPlayerIds() {
        return playerIds;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getDateTime() {
        return dateTime;
    }

    // SETTERS
    public void setTitle(String title) { this.title = title; }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDmId(String dmId) { this.dmId = dmId; }
    public void setPlayerIds(List<String> playerIds) { this.playerIds = playerIds; }
    public void setHostId(String hostId) {
        this.hostId = hostId;
        if(this.hostDm)
            setDmId(hostId);
        else
            addPlayerId(hostId);
    }

    public void addPlayerId(String playerId) { this.playerIds.add(playerId); }
}
