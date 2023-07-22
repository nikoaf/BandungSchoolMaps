package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Coblong {

    public static void regionCoblong(ArrayList<LatLng> latLngsCoblong, GoogleMap googleMap, LatLng latLng) {
        latLngsCoblong.add(new LatLng(-6.882754, 107.601016));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCoblong.add(new LatLng(-6.883247, 107.601198));

        if (RayCastingHelper.isPointPolyline(latLngsCoblong, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCoblong, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCoblong, googleMap).setVisible(false);
        }
    }
}
