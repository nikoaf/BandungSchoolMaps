package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Cicendo {

    public static void regionCicendo(ArrayList<LatLng> latLngsCicendo, GoogleMap googleMap, LatLng latLng) {
        latLngsCicendo.add(new LatLng(-6.888975, 107.556057));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCicendo.add(new LatLng(-6.891432, 107.557504));

        if (RayCastingHelper.isPointPolyline(latLngsCicendo, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCicendo, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCicendo, googleMap).setVisible(false);
        }
    }
}
