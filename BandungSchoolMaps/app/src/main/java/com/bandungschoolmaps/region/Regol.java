package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Regol {

    public static void regionRegol(ArrayList<LatLng> latLngsRegol, GoogleMap googleMap, LatLng latLng) {
        latLngsRegol.add(new LatLng(-6.920765, 107.604157));
        /**
         * ---
         * ---
         * ---
         */
        latLngsRegol.add(new LatLng(-6.922013, 107.604082));

        if (RayCastingHelper.isPointPolyline(latLngsRegol, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsRegol, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsRegol, googleMap).setVisible(false);
        }
    }
}
