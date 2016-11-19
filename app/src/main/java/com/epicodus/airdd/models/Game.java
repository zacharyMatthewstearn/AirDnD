package com.epicodus.airdd.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {
    private User mHost = null;
    private User mDM = null;
    private List<User> mPlayers = new ArrayList<>();
    private String mTitle = "";
    private String mDescription = "";
    private String mLocation = "";
    private Date mDateTime = null;

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
}
