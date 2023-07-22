package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class CibeunyingKaler {

    public static void regionCibeunyingKaler(ArrayList<LatLng> latLngsCibeunyingKaler, GoogleMap googleMap, LatLng latLng) {
        latLngsCibeunyingKaler.add(new LatLng(-6.866488, 107.625455));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCibeunyingKaler.add(new LatLng(-6.866594, 107.625722));

        if (RayCastingHelper.isPointPolyline(latLngsCibeunyingKaler, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibeunyingKaler, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibeunyingKaler, googleMap).setVisible(false);
        }
    }
}
