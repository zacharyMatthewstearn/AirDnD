package com.epicodus.airdd.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Game {

    // STATIC VARIABLES
    static List<Game> ALL_GAMES = new ArrayList<>();

    // MEMBER VARIABLES
    String hostId = "";
    String dmId = "";
    List<String> playerIds = new ArrayList<>();
    String title = "";
    String description = "";
    String location = "";
    String dateTime = "";

    // CONSTRUCTORS
    public Game(String _title, String _dateTime, String _location, String _description, String _hostId, boolean _hostDm) {
        this.title = _title;
        this.dateTime = _dateTime;
        this.location = _location;
        this.description = _description;
        this.hostId = _hostId;
        if(_hostDm)
            this.dmId = _hostId;
        else
            playerIds.add(_hostId);
    }
    public Game() {    }

    // STATIC METHODS
    public static Game findByTitle(List<Game> games, String title) {
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
    public void setHostId(String _hostId) { this.hostId = _hostId; }

    public void addPlayerId(String playerId) { this.playerIds.add(playerId); }
}
