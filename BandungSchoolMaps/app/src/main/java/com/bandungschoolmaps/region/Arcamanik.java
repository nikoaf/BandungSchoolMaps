package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Arcamanik {

    public static void regionArcamanik(ArrayList<LatLng> latLngsArcamanik, GoogleMap googleMap, LatLng latLng) {
        latLngsArcamanik.add(new LatLng(-6.938685, 107.669704));
        /**
         * ---
         * ---
         * ---
         */
        latLngsArcamanik.add(new LatLng(-6.938523, 107.669697));

        if (RayCastingHelper.isPointPolyline(latLngsArcamanik, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsArcamanik, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsArcamanik, googleMap).setVisible(false);
        }
    }
}
