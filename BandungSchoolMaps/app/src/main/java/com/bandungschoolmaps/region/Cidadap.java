package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Cidadap {

    public static void regionCidadap(ArrayList<LatLng> latLngsCidadap, GoogleMap googleMap, LatLng latLng) {
        latLngsCidadap.add(new LatLng(-6.843460, 107.598594));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCidadap.add(new LatLng(-6.842809, 107.598692));

        if (RayCastingHelper.isPointPolyline(latLngsCidadap, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCidadap, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCidadap, googleMap).setVisible(false);
        }
    }
}
