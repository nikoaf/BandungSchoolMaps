package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Antapani {

    public static void regionAntapani(ArrayList<LatLng> latLngsAntapani, GoogleMap googleMap, LatLng latLng) {
        latLngsAntapani.add(new LatLng(-6.932387, 107.666846));
        /**
         * ---
         * ---
         * ---
         */
        latLngsAntapani.add(new LatLng(-6.932169, 107.666910));

        if (RayCastingHelper.isPointPolyline(latLngsAntapani, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAntapani, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAntapani, googleMap).setVisible(false);
        }
    }
}
