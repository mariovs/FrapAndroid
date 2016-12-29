package com.mario22gmail.vasile.frap;

import android.content.Context;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mario22gmail.vasile.frap.MapComponent.MapContract;
import com.mario22gmail.vasile.frap.MapComponent.MapFragment;
import com.mario22gmail.vasile.frap.MapComponent.MapModel;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mario.vasile on 12/19/2016.
 */


public class MapUnitTest {

    @Mock
    Context mMockContext;

    @Mock
    MapFragment mMockMapFragment;

    @Mock
    MapContract.ModelOpsToPresenter modelOpsToPresenter;

    @Test
    public void FirstFrapTest()
    {
        MapModel model = new MapModel();

        verify(mMockMapFragment,times(1)).showMarkerOnMap(new LatLng(10,20));
    }
}
