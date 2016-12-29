package com.mario22gmail.vasile.frap.MapComponent;

/**
 * Created by Mario Vasile on 12/29/2016.
 */

public class FriendLocation {
    private double latitude;
    private double longitude;
    private String friendId;

    public FriendLocation(String friendId, double longitude, double latitude) {
        this.friendId = friendId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public FriendLocation() {
        super();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getFriendId() {
        return friendId;
    }
}
