package com.mario22gmail.vasile.frap.MapComponent;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mario22gmail.vasile.frap.BasePresenter;
import com.mario22gmail.vasile.frap.BaseView;

/**
 * Created by mario.vasile on 10/20/2016.
 */

public interface MapContract {

    interface ViewOps extends BaseView<PresenterOpsToView>{

        void showMyLocation(LatLng myLocation);

        void showMarkerOnMap(LatLng markerLocation);
    }


    interface PresenterOpsToView extends BasePresenter{

        void getMyLocation();

        void loadRandomPointOnMap(int intervalTime);
    }

    interface ModelCallbackToPresenter {

        void updateMyLocation(LatLng location);

        void updateMapWithMarkers(FriendLocation latLng);
    }

    interface ModelOpsToPresenter {

        void getMyLocation(ModelCallbackToPresenter callback);

        void getRandomMarkersLocation(int intervalTime, ModelCallbackToPresenter callback);
    }
}
