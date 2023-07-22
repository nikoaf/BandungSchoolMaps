package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Sukajadi {

    public static void regionSukajadi(ArrayList<LatLng> latLngsSukajadi, GoogleMap googleMap, LatLng latLng) {
        latLngsSukajadi.add(new LatLng(-6.890817, 107.576113));
        /**
         * ---
         * ---
         * ---
         */
        latLngsSukajadi.add(new LatLng(-6.890503, 107.576035));

        if (RayCastingHelper.isPointPolyline(latLngsSukajadi, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSukajadi, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSukajadi, googleMap).setVisible(false);
        }
    }
}
