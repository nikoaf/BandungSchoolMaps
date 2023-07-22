package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BandungKulon {

    public static void regionBandungKulon(ArrayList<LatLng> latLngsBandungKulon, GoogleMap googleMap, LatLng latLng) {
        latLngsBandungKulon.add(new LatLng(-6.910310, 107.569030));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBandungKulon.add(new LatLng(-6.915445, 107.572069));

        if (RayCastingHelper.isPointPolyline(latLngsBandungKulon, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungKulon, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungKulon, googleMap).setVisible(false);
        }
    }
}
