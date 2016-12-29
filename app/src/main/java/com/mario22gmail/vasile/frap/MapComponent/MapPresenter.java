package com.mario22gmail.vasile.frap.MapComponent;

import android.support.annotation.NonNull;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by mario.vasile on 10/20/2016.
 */

public class MapPresenter implements MapContract.PresenterOpsToView, MapContract.ModelCallbackToPresenter {

    private final MapContract.ViewOps mMapView;
    private final MapContract.ModelOpsToPresenter model;

    public MapPresenter(@NonNull MapContract.ViewOps mapView)
    {
        mMapView = mapView;
        model = new MapModel();
        mMapView.setPresenter(this);
    }

    @Override
    public void getMyLocation() {
        model.getMyLocation(this);
    }

    @Override
    public void loadRandomPointOnMap(int intervalTime) {
        model.getRandomMarkersLocation(intervalTime,this);
    }

    @Override
    public void start() {

    }

    @Override
    public void updateMyLocation(LatLng location) {
        mMapView.showMyLocation(location);

    }

    @Override
    public void updateMapWithMarkers(FriendLocation friend) {
        if(friend != null)
        {
            LatLng latLng = new LatLng(friend.getLatitude(),friend.getLongitude());
            mMapView.showMarkerOnMap(latLng);
        }
    }
}
