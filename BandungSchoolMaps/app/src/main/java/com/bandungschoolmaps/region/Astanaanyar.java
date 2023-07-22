package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Astanaanyar {

    public static void regionAstanaanyar(ArrayList<LatLng> latLngsAstanaanyar, GoogleMap googleMap, LatLng latLng) {
        latLngsAstanaanyar.add(new LatLng(-6.920765, 107.604157));
        /**
         * ---
         * ---
         * ---
         */
        latLngsAstanaanyar.add(new LatLng(-6.922013, 107.604082));

        if (RayCastingHelper.isPointPolyline(latLngsAstanaanyar, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAstanaanyar, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAstanaanyar, googleMap).setVisible(false);
        }
    }
}
