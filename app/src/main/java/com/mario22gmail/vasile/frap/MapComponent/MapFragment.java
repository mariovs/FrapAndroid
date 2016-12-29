package com.mario22gmail.vasile.frap.MapComponent;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mario22gmail.vasile.frap.MenuActivity;
import com.mario22gmail.vasile.frap.R;
import com.mario22gmail.vasile.frap.util.LatLngEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapFragment extends Fragment implements MapContract.ViewOps {

    //map
    private MapboxMap mapBox;
    private com.mapbox.mapboxsdk.maps.MapView mapViewMapBox;
    private ArrayList<Marker> markersList;

    private MapContract.PresenterOpsToView mapFragmentPresenter;


    //Empty constructor
    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void setPresenter(MapContract.PresenterOpsToView presenter) {
        mapFragmentPresenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        markersList = new ArrayList<>();

        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        mapViewMapBox = (com.mapbox.mapboxsdk.maps.MapView) layout.findViewById(R.id.mapview);
        mapViewMapBox.onCreate(savedInstanceState);


        mapViewMapBox.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                mapBox = mapboxMap;
                mapFragmentPresenter.getMyLocation();
                mapFragmentPresenter.loadRandomPointOnMap(2000);

            }
        });


        ImageButton myLocationButton = (ImageButton) layout.findViewById(R.id.myLocationButton);
        myLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragmentPresenter.getMyLocation();
            }
        });
        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapViewMapBox.onResume();
//        if(mapBox != null)
//        {
//            mapFragmentPresenter.getMyLocation();
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showMyLocation(LatLng myLocation) {

        if (mapBox != null) {
            Log.i("mapmario", myLocation.getLatitude() + " " + myLocation.getLongitude());
            Log.i("mapmario", myLocation.toString());
            mapBox.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
            mapBox.setMyLocationEnabled(true);

        }
    }

    @Override
    public void showMarkerOnMap(LatLng markerLocation) {
        IconFactory iconFactory = IconFactory.getInstance(MenuActivity.getActivity());
        Drawable iconDrawable = ContextCompat.getDrawable(MenuActivity.getActivity(), R.drawable.location_circle);
        final Icon iconUser = iconFactory.fromDrawable(iconDrawable);
        Marker marker =  mapBox.addMarker(new MarkerOptions()
                .position(markerLocation)
                .icon(iconUser)
                .title("I'm Mr Meeseeks !")
                .snippet("Welcome to my marker."));
        markersList.add(marker);

//
//        if(mapBox.getMarkers().size() == 0)
//        {
//
//        }
//        else {
//            Marker markerFromMaps = mapBox.getMarkers().get(0);
//            ValueAnimator markerAnimator = ObjectAnimator.ofObject(markerFromMaps, "position",
//                    new LatLngEvaluator(), markerFromMaps.getPosition(), markerLocation);
//            markerAnimator.setDuration(2000);
//            markerAnimator.start();
//        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

