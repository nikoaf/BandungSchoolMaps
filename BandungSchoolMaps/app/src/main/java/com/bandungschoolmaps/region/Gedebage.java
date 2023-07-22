package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Gedebage {

    public static void regionGedebage(ArrayList<LatLng> latLngsGedebage, GoogleMap googleMap, LatLng latLng) {
        latLngsGedebage.add(new LatLng(-6.937975, 107.679793));
        /**
         * ---
         * ---
         * ---
         */
        latLngsGedebage.add(new LatLng(-6.940126, 107.679577));

        if (RayCastingHelper.isPointPolyline(latLngsGedebage, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsGedebage, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsGedebage, googleMap).setVisible(false);
        }
    }
}
