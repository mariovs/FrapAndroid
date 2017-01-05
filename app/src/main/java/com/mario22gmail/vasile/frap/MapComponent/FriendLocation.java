package com.mario22gmail.vasile.frap.MapComponent;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Mario Vasile on 12/29/2016.
 */

@IgnoreExtraProperties
public class FriendLocation {
    public double latitude;
    public double longitude;
    public String friendId;

    public FriendLocation(String friendId, double longitude, double latitude) {
        this.friendId = friendId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public FriendLocation() {
        super();
    }
}
