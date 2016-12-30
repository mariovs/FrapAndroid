package com.mario22gmail.vasile.frap.MapComponent;

import android.Manifest;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telecom.Call;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mario22gmail.vasile.frap.MenuActivity;
import com.mario22gmail.vasile.frap.Service.MapService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mario.vasile on 10/27/2016.
 */

public class MapModel implements MapContract.ModelOpsToPresenter {
    public com.mapbox.mapboxsdk.location.LocationServices locationServices;
    private static final int PERMISSIONS_LOCATION = 0;
    private static String debugString = "FrapDebug";
    private static String altString ="altstring";   

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void getMyLocation(final MapContract.ModelCallbackToPresenter callback) {
        locationServices = com.mapbox.mapboxsdk.location.LocationServices.getLocationServices(MenuActivity.getAppContext());
        if (!locationServices.areLocationPermissionsGranted()) {
            ActivityCompat.requestPermissions(MenuActivity.getActivity(), new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_LOCATION);
        }
        Location lastLocation = locationServices.getLastLocation();
        if(lastLocation !=  null)
        {
            callback.updateMyLocation(new LatLng(lastLocation));
        }
//            Log.i("FrapDebug" , "locatia a fost gasita " + lastLocation.toString());
//            AsyncServerCommunication asyncServerCommunication = new AsyncServerCommunication();
//            asyncServerCommunication.execute(new LatLng(lastLocation));
//        }
//        callback.updateMyLocation(new LatLng(lastLocation));
    }


    @Override
    public void getRandomMarkersLocation(int intervalTime, final MapContract.ModelCallbackToPresenter modelCallbackToPresenter) {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.101:8081")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MapService mapService = retrofit.create(MapService.class);
                        retrofit2.Call<List<FriendLocation>> friendsLocation = mapService.GetFriends();
                        friendsLocation.enqueue(new Callback<List<FriendLocation>>() {
                            @Override
                            public void onResponse(retrofit2.Call<List<FriendLocation>> call, Response<List<FriendLocation>> response) {
                                if (response.isSuccessful()) {
                                    List<FriendLocation> friendLocationList = response.body();
                                    for(FriendLocation friend : friendLocationList)
                                    {
                                        modelCallbackToPresenter.updateMapWithMarkers(friend);
                                    }
                                } else {
                                    Log.i(debugString, "Response from frap server failed");
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<List<FriendLocation>> call, Throwable t) {
                                Log.i(debugString, "Failure from frap server failed");
                            }
                        });
//                        try {
//                            AsyncMarker asyncMarker = new AsyncMarker(modelCallbackToPresenter);
//                            // PerformBackgroundTask this class is the class that extends AsynchTask
//                            asyncMarker.execute();
//                        } catch (Exception e) {
//                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, intervalTime);
    }
}
