package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Andir {

    public static void regionAndir(ArrayList<LatLng> latLngsAndir, GoogleMap googleMap, LatLng latLng) {
        latLngsAndir.add(new LatLng(-6.891403, 107.564880));
        /**
         * ---
         * ---
         * ---
         */
        latLngsAndir.add(new LatLng(-6.891734, 107.565784));

        if (RayCastingHelper.isPointPolyline(latLngsAndir, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAndir, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsAndir, googleMap).setVisible(false);
        }
    }
}
