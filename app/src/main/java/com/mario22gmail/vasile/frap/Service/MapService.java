package com.mario22gmail.vasile.frap.Service;

import com.mario22gmail.vasile.frap.MapComponent.FriendLocation;
import com.mario22gmail.vasile.frap.MapComponent.MapModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mario Vasile on 12/26/2016.
 */

public interface MapService {

    @GET("/friends.json")
    Call<List<FriendLocation>> GetFriends();
}
